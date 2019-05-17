package ms.vm.lang.javaex;

import static ms.parser.ParserHelper.checkErrors;
import static ms.parser.ParserHelper.getParser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms.lang.Language;
import ms.lang.types.IType;
import ms.lang.types.Type;
import ms.parser.error.ParseError;
import ms.vm.lang.javaex.gen.JavaHeaderLexer;
import ms.vm.lang.javaex.gen.JavaHeaderParser;
import ms.vm.lang.javaex.gen.JavaHeaderParser.ClassIdentifierContext;
import ms.vm.lang.javaex.gen.JavaHeaderParser.CompilationUnitContext;
import ms.vm.lang.javaex.gen.JavaHeaderParser.InterfaceModifierContext;
import ms.vm.lang.javaex.gen.JavaHeaderParser.PackageNameContext;
import ms.vm.lang.javaex.gen.JavaHeaderParserBaseListener;

public class JavaTypeExtractor extends JavaHeaderParserBaseListener {
	private String firstPublicName;
	private boolean isCurrentClassPublic = false;

	private String packageName;
	private List<IType> classes;
	private final String source;

	private static final Logger logger = LogManager.getLogger();

	public JavaTypeExtractor(String source) {
		this.source = source;
		JavaHeaderParser parser = getParser(source, JavaHeaderLexer::new, JavaHeaderParser::new);
		CompilationUnitContext unit = parser.compilationUnit();
		checkErrors(parser, "Cannot extract the compilation unit name", ParseError.class);
		logger.trace("Tree for '{}' is:\t{}", () -> source, () -> unit.toStringTree(parser));
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(this, unit);
	}

	public List<IType> getPrototypes() {
		return classes;
	}

	public String getPublic() {
		return firstPublicName;
	}

	@Override
	public void enterPackageName(PackageNameContext ctx) {
		packageName = ctx.getText();
	}

	@Override
	public void enterCompilationUnit(CompilationUnitContext ctx) {
		firstPublicName = null;
		packageName = null;
		isCurrentClassPublic = false;
		classes = new ArrayList<>();
	}

	@Override
	public void exitInterfaceModifier(InterfaceModifierContext ctx) {
		isCurrentClassPublic = ctx.getText().equals("public");
	}

	@Override
	public void exitClassIdentifier(ClassIdentifierContext ctx) {
		if (isCurrentClassPublic) {
			if (firstPublicName != null) {
				throw new IllegalArgumentException("Second public class encountered '" + ctx.getText()
						+ "' in addition to '" + firstPublicName + "'");
			}
			firstPublicName = ctx.getText();
		}
		Type prototype = Type.prototype(packageName, ctx.getText(), Language.JAVA, source);
		// type extract is only used for dynamically compiling classes which
		// will should therefore be fleeting
		prototype.setFleeting(true);
		classes.add(prototype);
	}
}