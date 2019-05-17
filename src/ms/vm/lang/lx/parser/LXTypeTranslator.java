package ms.vm.lang.lx.parser;

import static ms.ipp.Iterables.appendList;
import static ms.ipp.Iterables.first;
import static ms.ipp.Iterables.map;
import static ms.ipp.iterable.tree.path.StdPathManipulator.toPath;
import static ms.lang.DeclarationHelper.isAbstract;
import static ms.lang.Language.LX;
import static ms.lang.java.JavaDeclarationHelper.createJavaConstructors;
import static ms.lang.java.JavaDeclarationHelper.isJavaInterface;
import static ms.vm.lang.lx.parser.ParserHelper.parse;

import java.util.List;
import java.util.function.BiFunction;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import ms.lang.java.JavaDeclarationHelper;
import ms.lang.types.BaseConstructor;
import ms.lang.types.BaseMethod;
import ms.lang.types.ClassVariable;
import ms.lang.types.IType;
import ms.lang.types.Type;
import ms.lang.types.TypeName;
import ms.vm.lang.lx.LXDeclarationHelper;
import ms.vm.lang.lx.gen.ObjectParser;
import ms.vm.lang.lx.gen.ObjectParser.BlockContext;
import ms.vm.lang.lx.gen.ObjectParser.ClassVarContext;
import ms.vm.lang.lx.gen.ObjectParser.ImportClassContext;
import ms.vm.lang.lx.gen.ObjectParser.MethodContext;
import ms.vm.lang.lx.gen.ObjectParser.TypeContext;
import ms.vm.lang.lx.gen.ObjectParser.VarDeclaratorContext;

public class LXTypeTranslator extends AbstractTranslator<TypeContext> {

	public LXTypeTranslator(BiFunction<List<String>, String, IType> typer) {
		super(typer);
	}

	@Override
	protected IType createThisClass() {
		ObjectParser.HeaderContext h = rule.header();
		TypeName thisName = parse(h.typeName(0), null);
		String pack = rule.packageClause().packageName().getText();
		thisName.setFullName(pack + "." + thisName.getName());
		IType metadata = new Type(thisName, LX, parse(h.typeName(1), tracker));
		if (logger.isTraceEnabled()) {
			logger.debug("Creating type: " + LXDeclarationHelper.lxDeclaration(metadata));
		}
		return metadata;
	}

	@Override
	protected void insertImport(String className) {
		logger.debug("Inserting import '" + className + "'");
		Token token = (rule.importClass().isEmpty()) ? rule.header().getStart() : rule.importClass(0).getStart();
		rewriter.insertBefore(token, "import " + className + ";\n");
	}

	@Override
	protected TypeContext extractRule(ObjectParser parser) {
		return parser.type();
	}

	@Override
	protected List<ImportClassContext> getImports() {
		return rule.importClass();
	}

	@Override
	protected String getPackage() {
		return toPath(map(rule.packageClause().packageName().ID(), TerminalNode::getText));
	}

	@Override
	protected void finish() {
		IType thisClass = getThisClass();
		boolean typeAbstract = isAbstract(thisClass, tracker::getType);
		thisClass.setAbstract(typeAbstract);
		String header = typeAbstract ? "public abstract class" : "public class";
		rewriter.replace(rule.header().TYPE().getSymbol(), header);
		if (rule.header().EXTENDS() != null) {
			IType baseType = tracker.getType(thisClass.getBaseName());
			// if we extend an interface, Java requires the "implements"-keyword
			if (isJavaInterface(baseType)) {
				rewriter.replace(rule.header().EXTENDS().getSymbol(), "implements");
			}
		}

		List<BaseConstructor> constructors = createJavaConstructors(thisClass, getSuperConstructor(thisClass));
		for (BaseConstructor c : constructors) {
			thisClass.addConstructor(c, null);
			c.getArguments().forEach(v -> tracker.addImport(v.getTypeName().getImport(), true));
		}

		rewriter.replace(rule.header().getStop(), "{");
		rewriter.insertAfter(rule.getStop(), "\n\n" + appendList(constructors, "", "", "\n",
				(c, s) -> s.append(JavaDeclarationHelper.javaDeclaration(c, true, false))) + "\n}");
	}

	@Override
	public void enterMethod(MethodContext ctx) {
		BaseMethod m = parse(ctx, tracker, rewriter, LXExpressionTranslator::translate);
		if (logger.isTraceEnabled()) {
			logger.debug("Parsed method: " + JavaDeclarationHelper.javaDeclaration(m, true, false));
		}

		getThisClass().addMethod(m, null);
		tracker.pushStack();

		// remove default values for parameters.
		// (they will be used in the named-param method calls)
		for (VarDeclaratorContext v : ctx.paramDef().varDeclarator()) {
			if (v.expression() != null) {
				rewriter.delete(v.EQ().getSymbol(), v.expression().getStop());
			}
		}
		// add 'abstract'-keyword for methods without body
		if (m.isAbstract()) {
			rewriter.insertBefore(ctx.returnType().getStart(), "abstract ");
		}
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		tracker.pushStack();
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		tracker.popStack();
	}

	@Override
	public void exitMethod(MethodContext ctx) {
		tracker.popStack();
	}

	@Override
	public void exitClassVar(ClassVarContext ctx) {
		List<ClassVariable> vars = parse(ctx, tracker, rewriter, LXExpressionTranslator::translate);
		vars.forEach(v -> tracker.setGlobal(v.getName(), v));
		if (logger.isTraceEnabled()) {
			logger.trace("ClassVar: " + rewriter.getText(ctx.getSourceInterval()));
		}
	}

	/**
	 * if there is no empty constructor in the super-class -> pick the first one you
	 * find and add its params to the constructor
	 * 
	 * @return
	 */

	private BaseMethod getSuperConstructor(IType thisClass) {
		if (thisClass.getBaseName() == null) {
			return null;
		}
		IType base = tracker.getType(thisClass.getBaseName());
		if (base == null) {
			return null;
		}
		BaseMethod superConstructor = base.getConstructor();
		if (superConstructor == null) {
			superConstructor = first(base.constructors(null), null);
		}
		return superConstructor;
	}
}
