package ms.vm.lang.lx;

import static ms.ipp.Iterables.appendList;
import static ms.ipp.Iterables.list;
import static ms.ipp.Iterables.unique;
import static ms.lang.DeclarationHelper.varCall;
import static ms.lang.java.JavaDeclarationHelper.isVoid;
import static ms.lang.java.JavaDeclarationHelper.javaMethodName;

import java.util.ArrayList;
import java.util.List;

import ms.ipp.base.KeyValue;
import ms.lang.java.JavaDeclarationHelper;
import ms.lang.types.BaseMethod;
import ms.lang.types.Definition;
import ms.lang.types.IType;
import ms.lang.types.TypeName;
import ms.lang.types.Variable;

public class LXDeclarationHelper {

	public static String lxDeclaration(IType type) {
		StringBuffer sb = new StringBuffer(10000);
		String pack = type.getPackage();
		if (pack != null) {
			sb.append("package ").append(pack).append(";\n");
		}
		List<String> imports = type.collectImports(false);
		imports = list(imports, i -> !i.startsWith("java.lang"));

		if (!imports.isEmpty()) {
			appendList(sb, imports, "\n", "", "", (i, s) -> s.append("import ").append(i).append(";\n"));
		}
		sb.append("\n");
		sb.append("type ").append(type.getSimpleName());
		if (type.getBase() != null) {
			sb.append(" extends ").append(type.getBase().serialize());
		}
		sb.append(";\n\n");

		for (Definition d : type.definitions(null)) {
			if (d instanceof BaseMethod) {
				BaseMethod m = (BaseMethod) d;
				sb.append(JavaDeclarationHelper.javaDeclaration(m, true, true));
			} else {
				Variable v = (Variable) d;
				v.serialize(sb, true, false);
				sb.append(";");
			}
			sb.append("\n\n");
		}

		return sb.toString();
	}

	/**
	 * Re-writes named-param call convention by means of a lambda-expression. This
	 * permits us to replace method calls anywhere in the code.
	 * 
	 * @param method
	 * @param nargs
	 * @return
	 */
	public static String namedCall(BaseMethod method, List<NamedParam> nargs) {
		StringBuffer sb = new StringBuffer(1000);
		KeyValue<String, String> frame = getLambdaFrame(method.getReturnType());
		sb.append(frame.getKey()).append("\n");

		// we may modify the collection -> do it on a copy
		List<NamedParam> params = new ArrayList<>(nargs);
		List<String> inited = new ArrayList<>();
		// append default values first in the order of their declaration;
		// (rewrite them if forced to)
		for (Variable arg : method.getArguments()) {
			String paramName = arg.getName();
			TypeName type = arg.getTypeName();
			String expr = arg.getExpression();
			NamedParam p = unique(params, n -> n.isRewriteDefault() && n.getId().equals(paramName));
			if (p != null) {
				expr = p.getExpression();
				params.remove(p);
			}
			if (expr == null) {
				continue;
			}
			appendParam(sb, true, type, paramName, "=", expr, method.getName());
			inited.add(paramName);
		}

		// append all remaining modifications in the method invocations
		for (NamedParam p : params) {
			String paramName = p.getId();
			String assOp = p.getAssignment();
			Variable declVar = method.getArgument(paramName);
			// unknown argument
			if (declVar == null) {
				throw new IllegalArgumentException(
						"Unknown argument '" + paramName + "' while invoking method '" + method.getName() + "'");
			}
			// if the param had no default value, we need to declare it
			TypeName type = declVar.getTypeName();
			appendParam(sb, !inited.contains(paramName), type, paramName, assOp, p.getExpression(), method.getName());
		}

		// append the method call
		if (!isVoid(method.getReturnType())) {
			sb.append("return ");
		}
		sb.append(varCall(javaMethodName(method), method.getArguments())).append(";");
		sb.append(frame.getValue());
		return sb.toString();
	}

	private static void appendParam(StringBuffer sb, boolean declare, TypeName type, String name, String assOp,
			String expression, String method) {
		if (declare) {
			// modifying assign(such as +=) but no def-value to modify
			if (assOp.length() == 2) {
				throw new IllegalArgumentException("Cannot modify argument '" + name
						+ "' without default value while invoking method '" + method + "'");
			}
			sb.append(type.serialize()).append(" ");
		}
		sb.append(name).append(" ").append(assOp).append(" ");
		// the expression has already been re-written on exitExpression
		sb.append(expression).append(";\n");
	}

	private static KeyValue<String, String> getLambdaFrame(TypeName type) {
		String supplier = "", method = "";
		JavaDeclarationHelper.JavaPrimitiveTraits t = null;
		if (JavaDeclarationHelper.isVoid(type)) {
			supplier = "Runnable";
			method = "run";
		} else if ((t = JavaDeclarationHelper.getTraits(type.getName())) != null) {
			supplier = t.getSupplierName();
			method = t.getGetterName();
		} else {
			supplier = "java.util.function.Supplier<" + type.serialize() + ">";
			method = "get";
		}

		return new KeyValue<>("((" + supplier + ") (() -> {", "}))." + method + "()");
	}

}
