package ms.vm.lang.lx.parser;

import static ms.ipp.Iterables.appendList;
import static ms.ipp.Iterables.map;
import static ms.lang.types.Mod.PRIVATE;
import static ms.lang.types.VarModifier.LOCAL;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ms.ipp.base.KeyValue;
import ms.lang.types.BaseMethod;
import ms.lang.types.ClassVariable;
import ms.lang.types.IType;
import ms.lang.types.Mod;
import ms.lang.types.TypeName;
import ms.lang.types.VarModifier;
import ms.lang.types.Variable;
import ms.vm.lang.lx.gen.ObjectLexer;
import ms.vm.lang.lx.gen.ObjectParser;
import ms.vm.lang.lx.gen.ObjectParser.ClassVarContext;
import ms.vm.lang.lx.gen.ObjectParser.ExpressionContext;
import ms.vm.lang.lx.gen.ObjectParser.ExpressionNameContext;
import ms.vm.lang.lx.gen.ObjectParser.LocalVarContext;
import ms.vm.lang.lx.gen.ObjectParser.MethodContext;
import ms.vm.lang.lx.gen.ObjectParser.ParamDefContext;
import ms.vm.lang.lx.gen.ObjectParser.ReturnTypeContext;
import ms.vm.lang.lx.gen.ObjectParser.TemplateArgsContext;
import ms.vm.lang.lx.gen.ObjectParser.TypeNameContext;
import ms.vm.lang.lx.gen.ObjectParser.VarDeclaratorContext;

public class ParserHelper {
	public static interface Translator {
		String translate(TypeConstructor tracker, TokenStreamRewriter rewriter, ParserRuleContext ctx);
	}

	public static KeyValue<String, String> getQualifiedName(ExpressionNameContext ctx) {
		if (ctx == null) {
			return null;
		}
		List<String> ids = new ArrayList<>();
		if (ctx.THIS() != null) {
			ids.add(ctx.THIS().getText());
		} else if (ctx.SUPER() != null) {
			ids.add(ctx.SUPER().getText());
		}
		for (int i = 0; i < ctx.ID().size() - 1; ++i) {
			ids.add(ctx.ID(i).getText());
		}
		String prefix = appendList(ids, "", "", ".", (s, sb1) -> sb1.append(s));
		return new KeyValue<String, String>(prefix, ctx.ID(ctx.ID().size() - 1).getText());
	}

	public static List<ClassVariable> parse(ClassVarContext ctx, TypeConstructor tracker, TokenStreamRewriter rewriter,
			Translator translator) {
		Mod mod = Mod.parse(ctx.mod().getText());
		VarModifier varMod = VarModifier.parse(ctx.varMod() == null ? null : ctx.varMod().getText());
		boolean isStatic = ctx.STATIC() != null;
		return parse(mod, varMod, isStatic, ctx.localVar(), tracker, rewriter, translator);
	}

	public static BaseMethod parse(MethodContext ctx, TypeConstructor tracker, TokenStreamRewriter rewriter,
			Translator translator) {
		Mod mod = Mod.parse(ctx.mod().getText());
		TypeName returnType = parseReturnType(ctx.returnType(), tracker);
		String name = ctx.methodName().getText();
		boolean isAbstract = ctx.body() == null;
		String body = isAbstract ? null : ctx.body().getText();
		boolean isStatic = ctx.STATIC() != null;

		if (isStatic && isAbstract) {
			throw new IllegalArgumentException("Static method '" + name + "' must have a body");
		}
		BaseMethod def = new BaseMethod(mod, isStatic, returnType, name, body);

		ParamDefContext params = ctx.paramDef();
		// all params with default values must depend only on the initilised(!)
		// parameters to their left in the declarations. This should avoid
		// situations like this:
		// void method(int b, int a=2*b);
		// method(a+=x, b=2*a);
		// where it is unclear which default value "a" should have
		List<Variable> args = new ArrayList<>();
		if (params != null) {
			// Assert.assertEquals(params.typeName().size(),
			// params.varDeclarator().size());
			for (int i = 0; i < params.typeName().size(); ++i) {
				TypeName typeName = parse(params.typeName(i), tracker);
				VarDeclaratorContext varDecl = params.varDeclarator(i);
				ClassVariable var = parse(PRIVATE, LOCAL, false, typeName, varDecl, tracker, rewriter, translator);
				if (tracker != null) {
					tracker.set(var.getName(), var);
				}
				args.add(var);
			}
		}
		def.setArguments(args);
		return def;

	}

	public static List<ClassVariable> parse(Mod mod, VarModifier varMod, boolean isStatic, LocalVarContext ctx,
			TypeConstructor tracker, TokenStreamRewriter rewriter, Translator translator) {
		List<ClassVariable> result = new ArrayList<>();
		TypeName type = parse(ctx.typeName(), tracker);
		for (VarDeclaratorContext c : ctx.varDeclarator()) {
			result.add(parse(mod, varMod, isStatic, type, c, tracker, rewriter, translator));
		}
		return result;
	}

	public static ClassVariable parse(Mod mod, VarModifier varMod, boolean isStatic, TypeName type,
			VarDeclaratorContext var, TypeConstructor tracker, TokenStreamRewriter rewriter, Translator translator) {
		String id = var.varName().getText();
		ExpressionContext expr = var.expression();

		// error if there is at least one uninited variable in the definition
		if (tracker != null) {
			List<Variable> uninited = tracker.getVariables(expr, true, v -> v.getExpression() == null);

			if (!uninited.isEmpty()) {
				throw new IllegalArgumentException(
						"Variable '" + id + "' depends on uninitialised variables " + map(uninited, v -> v.getName()));
			}
		}
		if (varMod == VarModifier.DYNAMIC) {
			// TODO: add the new variable to the list of dependents for all
			// dynamic variables used in the defining expression
			// List<Variable> dyns = tracker.getVariables(expr, true,
			// v->v.getModifier() == DYNAMIC);
		}

		// if not translator, at least add the original expression.
		String translated = expr != null ? expr.getText() : null;
		if (translator != null) {
			translated = translator.translate(tracker, rewriter, expr);
		}
		return new ClassVariable(mod, varMod, isStatic, type, id, translated);
	}

	public static TypeName parse(TypeNameContext source, TypeConstructor tracker) {
		if (source == null) {
			return null;
		}
		String id = appendList(source.ID(), "", "", ".", (i, sb) -> sb.append(i.getText()));
		boolean isTemplate = source.templateArgs() != null;
		TypeName result = new TypeName(id, isTemplate);

		// add import if needed
		if (tracker != null) {
			IType checkType = tracker.getType(id);
			if (checkType == null) {
				throw new IllegalArgumentException("Unknown type '" + id + "'");
			}
			result.setFullName(checkType.getFullName());
		}

		if (isTemplate) {
			TemplateArgsContext ctx = source.templateArgs();
			for (TypeNameContext type : ctx.typeName()) {
				result.addTemplateArg(parse(type, tracker));
			}
		}

		return result;
	}

	public static <T, U extends Throwable> void verify(String text, Function<ObjectParser, T> ruleGenerator,
			String message, Class<U> errorClass, boolean consumeAll) throws U {
		ObjectParser parser = ms.parser.ParserHelper.getParser(text, s -> new ObjectLexer(s), s -> new ObjectParser(s));
		ruleGenerator.apply(parser);
		ms.parser.ParserHelper.checkErrors(parser, message, errorClass);
		if (consumeAll && !isEntireInputMatched(parser, text)) {
			throw ms.parser.ParserHelper.createError(errorClass, message, null);
		}
	}

	private static boolean isEntireInputMatched(Parser parser, String input) {
		return parser.getCurrentToken().getStartIndex() == input.length();
	}

	private static TypeName parseReturnType(ReturnTypeContext ctx, TypeConstructor tracker) {
		if (ctx.typeName() != null) {
			return parse(ctx.typeName(), tracker);
		} else if (ctx.VOID() != null) {
			return new TypeName(ctx.VOID().getText(), false);
		}
		throw new IllegalArgumentException("Unrecognized return type " + ctx.getText());
	}
}
