package ms.vm.lang.lx.parser;

import static ms.ipp.Iterables.map;
import static ms.lang.DeclarationHelper.call;
import static ms.lang.java.JavaDeclarationHelper.javaMethodName;
import static ms.vm.lang.lx.LXDeclarationHelper.namedCall;

import java.util.List;
import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import ms.ipp.iterable.tree.path.StdPathManipulator;
import ms.lang.types.BaseMethod;
import ms.lang.types.IType;
import ms.lang.types.TypeName;
import ms.utils.DateHelper;
import ms.vm.lang.lx.LXDeclarationHelper;
import ms.vm.lang.lx.NamedParam;
import ms.vm.lang.lx.gen.ObjectParser.ArgumentListContext;
import ms.vm.lang.lx.gen.ObjectParser.GenericObjCreationContext;
import ms.vm.lang.lx.gen.ObjectParser.MethodInvocation_baseContext;
import ms.vm.lang.lx.gen.ObjectParser.NamedArgumentContext;
import ms.vm.lang.lx.gen.ObjectParser.StdObjCreationContext;
import ms.vm.lang.lx.gen.ObjectParserBaseListener;

public class LXExpressionTranslator extends ObjectParserBaseListener {

	public static NamedParam toNamedParam(NamedArgumentContext ctx, Function<RuleContext, String> expr) {
		String id = ctx.varName().getText();
		String assign = ctx.namedAssignment().getText();
		boolean rewriteDefault = ctx.namedAssignment().assignmentOperator() == null;
		if (rewriteDefault) {
			assign = assign.substring(1); // forget the ":" in front of =
		}
		String expression = expr.apply(ctx.expression());
		return new NamedParam(id, assign, rewriteDefault, expression);
	}

	/**
	 * Should be used for compiling expressions.
	 * 
	 * @param className
	 * @param expr
	 * @return
	 */
	public static String translate(TypeConstructor tracker, TokenStreamRewriter rewriter, ParserRuleContext expr) {
		if (expr == null) {
			return null;
		}
		ParseTreeWalker walker = new ParseTreeWalker();
		ExprTranslator translator = new ExprTranslator(tracker, rewriter);
		walker.walk(translator, expr);
		return translator.getRewriter().getText(expr.getSourceInterval());
	}

	private static class ExprTranslator extends ObjectParserBaseListener {
		private final TypeConstructor tracker;
		private final TokenStreamRewriter rewriter;

		public ExprTranslator(TypeConstructor tracker, TokenStreamRewriter rewriter) {
			this.tracker = tracker;
			this.rewriter = rewriter;
		}

		@Override
		public void exitStdObjCreation(StdObjCreationContext ctx) {
			if (ctx.DATETIME() != null) {
				replaceDateTime(ctx.DATETIME());
			}
		}

		@Override
		public void exitMethodInvocation_base(MethodInvocation_baseContext ctx) {
			// either no params or standard calling convention
			ArgumentListContext args = ctx.argumentList();
			if (args == null || args.namedArgumentList() == null) {
				return;
			}
			String methodName = StdPathManipulator.toPath(map(ctx.expressionName().ID(), i -> i.getText()));
			// Named-param calling conventions can only be used on
			// non-overloaded methods
			BaseMethod method = tracker.getMethod(methodName, null);

			if (method == null) {
				throw new IllegalArgumentException("Method " + methodName
						+ " is not augmented and cannot be called by named-parameter convention.");
			}
			String invocation = LXDeclarationHelper.namedCall(method, toNamedParams(args));
			getRewriter().replace(ctx.getStart(), ctx.getStop(), invocation);
		}

		private List<NamedParam> toNamedParams(ArgumentListContext args) {
			return map(args.namedArgumentList().namedArgument(),
					n -> toNamedParam(n, r -> getRewriter().getText(r.getSourceInterval())));
		}

		@Override
		public void exitGenericObjCreation(GenericObjCreationContext ctx) {
			TypeName typeName = ParserHelper.parse(ctx.typeName(), tracker);
			IType type = tracker.getType(typeName.getName());
			BaseMethod constructor = type.getStdConstructor();

			String call = null;
			ArgumentListContext args = ctx.argumentList();
			if (args == null || args.simpleArgumentList() != null) {
				// the expressions must have already been translated
				List<String> res = map(args.simpleArgumentList().expression(),
						e -> getRewriter().getText(e.getSourceInterval()));
				call = call(javaMethodName(constructor), res);
			} else {
				call = namedCall(constructor, toNamedParams(args));
			}
			getRewriter().replace(ctx.getStart(), ctx.getStop(), call);
		}

		private void replaceDateTime(TerminalNode dateNode) {
			tracker.addImport(DateHelper.class.getCanonicalName(), true);
			String text = dateNode.getText();
			text = text.substring(1, text.length() - 1); // get rid of ''
			String date = null, time = "00:00:00,000";

			if (text.contains(" ")) {// both date and time
				int firstWS = text.indexOf(" "), lastWS = text.lastIndexOf(" ");
				date = text.substring(0, firstWS);
				time = text.substring(lastWS + 1);
			} else if (text.contains(":")) { // only time
				time = text;
			} else { // only date
				date = text;
			}
			getRewriter().replace(dateNode.getSymbol(), dateToJava(date, time));
		}

		private static String dateToJava(String date, String time) {
			StringBuffer sb = new StringBuffer(100);
			if (date != null) {
				sb.append("DateHelper.get(");
				sb.append(date.substring(0, 4)).append(", ");
				sb.append(date.substring(5, 7)).append(", ");
				sb.append(date.substring(8)).append(", ");
			} else {
				sb.append("DateHelper.getToday(");
			}

			sb.append(time.substring(0, 2)).append(", ");
			sb.append(time.substring(3, 5)).append(", ");
			sb.append(time.substring(6, 8)).append(", ");
			String millis = time.contains(",") ? time.substring(9) : "000";
			sb.append(millis).append(")");
			return sb.toString();
		}

		private TokenStreamRewriter getRewriter() {
			return rewriter;
		}
	}

}
