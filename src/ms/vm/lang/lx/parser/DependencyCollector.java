package ms.vm.lang.lx.parser;

import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ms.vm.lang.lx.gen.ObjectParser.ExpressionNameContext;
import ms.vm.lang.lx.gen.ObjectParser.LeftHandSideContext;
import ms.vm.lang.lx.gen.ObjectParser.MethodInvocationContext;
import ms.vm.lang.lx.gen.ObjectParser.MethodInvocation_baseContext;
import ms.vm.lang.lx.gen.ObjectParser.Primary_headerContext;
import ms.ipp.base.KeyValue;
import ms.vm.lang.lx.gen.ObjectParserBaseListener;

public class DependencyCollector {

	public static Set<String> collect(ParseTree context, boolean collectMethods) {
		ParseTreeWalker walker = new ParseTreeWalker();
		DependencyListener dl = new DependencyListener(collectMethods);
		walker.walk(dl, context);
		return dl.getDependencies();
	}

	private static class DependencyListener extends ObjectParserBaseListener {
		private final Set<String> dependencies;
		private final boolean collectMethods;

		public DependencyListener(boolean collectMethods) {
			dependencies = new TreeSet<>();
			this.collectMethods = collectMethods;
		}

		public Set<String> getDependencies() {
			return dependencies;
		}

		@Override
		public void exitMethodInvocation(MethodInvocationContext ctx) {
			// Process only the direct-call, the primary-based one will
			// be handled in the primary_header
			if (ctx.methodInvocation_base() != null) {
				processMethodInvocation(ctx.methodInvocation_base());
			}
		}

		@Override
		public void exitLeftHandSide(LeftHandSideContext ctx) {
			// Process only the direct-call/access, the primary-based one will
			// be handled in the primary_header
			if (ctx.methodInvocation_base() != null) {
				processMethodInvocation(ctx.methodInvocation_base());
			} else if (ctx.expressionName() != null) {
				processExpressionName(ctx.expressionName());
			}
		}

		@Override
		public void exitPrimary_header(Primary_headerContext ctx) {
			// the objects behind the nested calls are difficult to impossible
			// to identify at compile-time. Hence, we collect only direct
			// dependencies.
			if (ctx.methodInvocation_base() != null) {
				processMethodInvocation(ctx.methodInvocation_base());
			} else if (ctx.expressionName() != null) {
				processExpressionName(ctx.expressionName());
			}
		}

		private void processMethodInvocation(MethodInvocation_baseContext ctx) {
			// Complex Objects cannot be automatically dynamic and, if they
			// still want to behave like dynamic vars, they can add an
			// updateDependents() in their methods whenever necessary.
			if (collectMethods) {
				KeyValue<String, String> qualiName = ParserHelper.getQualifiedName(ctx.expressionName());
				if (qualiName.getKey().isEmpty()) {
					// if we encounter x(), x is surely not a method parameter
					dependencies.add("this");
				} else {
					dependencies.add(qualiName.getKey());
				}
			}
		}

		private void processExpressionName(ExpressionNameContext ctx) {
			dependencies.add(ctx.getText());
		}
	}
}
