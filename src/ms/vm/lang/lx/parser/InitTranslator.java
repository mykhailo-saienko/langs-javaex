package ms.vm.lang.lx.parser;

import static ms.lang.types.Mod.PRIVATE;
import static ms.lang.types.VarModifier.FINAL;
import static ms.vm.lang.lx.parser.LXExpressionTranslator.toNamedParam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import ms.lang.Language;
import ms.lang.types.BaseMethod;
import ms.lang.types.ClassVariable;
import ms.lang.types.IType;
import ms.lang.types.Type;
import ms.lang.types.TypeName;
import ms.vm.lang.lx.LXDeclarationHelper;
import ms.vm.lang.lx.NamedParam;
import ms.vm.lang.lx.gen.ObjectParser;
import ms.vm.lang.lx.gen.ObjectParser.HeaderInitContext;
import ms.vm.lang.lx.gen.ObjectParser.ImportClassContext;
import ms.vm.lang.lx.gen.ObjectParser.LocalVarContext;
import ms.vm.lang.lx.gen.ObjectParser.SuperModContext;
import ms.vm.lang.lx.gen.ObjectParser.TypeInitContext;

public class InitTranslator extends AbstractTranslator<TypeInitContext> {

	private final List<NamedParam> superMods;
	private TypeName initType;
	private final Object className;
	private final Object staticMethod;

	public InitTranslator(BiFunction<List<String>, String, IType> typer, String className, String staticMethod) {
		super(typer);

		this.className = className;
		this.staticMethod = staticMethod;
		initType = null;
		superMods = new ArrayList<>();
	}

	@Override
	protected IType createThisClass() {
		IType metadata = new Type(new TypeName("Init", false), Language.JAVA);
		return metadata;
	}

	@Override
	protected void finish() {
		IType targetType = tracker.getType(initType.getName());
		BaseMethod constructor = targetType.getStdConstructor();
		String translated = LXDeclarationHelper.namedCall(constructor, superMods);

		StringBuffer sb = new StringBuffer(2000);
		sb.append("public static ").append(initType.serialize()).append(" ").append(staticMethod).append("(){\n");
		sb.append("return ").append(translated).append(";");
		sb.append("\n}\n");

		rewriter.replace(rule.headerInit().getStart(), rule.headerInit().getStop(), "public class " + className + " {");
		rewriter.insertAfter(rule.getStop(), "\n\n" + sb.toString() + "\n}");
	}

	@Override
	protected List<ImportClassContext> getImports() {
		return rule.importClass();
	}

	@Override
	protected void insertImport(String className) {
		rewriter.insertBefore(rule.headerInit().getStart(), "import " + className + ";\n");
	}

	@Override
	protected TypeInitContext extractRule(ObjectParser parser) {
		return parser.typeInit();
	}

	@Override
	public void exitLocalVar(LocalVarContext ctx) {
		List<ClassVariable> vars = ParserHelper.parse(PRIVATE, FINAL, true, ctx, tracker, rewriter,
				LXExpressionTranslator::translate);
		vars.forEach(v -> tracker.setGlobal(v.getName(), v));
		rewriter.insertBefore(ctx.getStart(), "private static final ");
	}

	@Override
	public void exitHeaderInit(HeaderInitContext ctx) {
		initType = ParserHelper.parse(ctx.typeName(), tracker);
	}

	@Override
	public void exitSuperMod(SuperModContext ctx) {
		NamedParam np = toNamedParam(ctx.namedArgument(), r -> rewriter.getText(r.getSourceInterval()));
		rewriter.delete(ctx.getStart(), ctx.getStop());
		superMods.add(np);
	}
}
