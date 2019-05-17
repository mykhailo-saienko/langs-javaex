package ms.vm.lang.lx.parser;

import static ms.parser.ParserHelper.checkErrors;
import static ms.parser.ParserHelper.getParser;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms.lang.types.IType;
import ms.lang.types.TranslatedType;
import ms.parser.error.ParseError;
import ms.vm.lang.lx.gen.ObjectLexer;
import ms.vm.lang.lx.gen.ObjectParser;
import ms.vm.lang.lx.gen.ObjectParser.ImportClassContext;
import ms.vm.lang.lx.gen.ObjectParserBaseListener;

public abstract class AbstractTranslator<T extends ParseTree> extends ObjectParserBaseListener {
	protected static final Logger logger = LogManager.getLogger();

	private final BiFunction<List<String>, String, IType> typer;

	protected TypeConstructor tracker;
	protected TokenStreamRewriter rewriter;
	protected T rule;

	private final List<Consumer<IType>> postProcessors;

	public TranslatedType translate(String lxSource) {
		initialize(lxSource);

		// process all members
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(this, rule);

		finish();

		return new TranslatedType(getThisClass(), rewriter.getText());
	}

	AbstractTranslator(BiFunction<List<String>, String, IType> typer) {
		this.typer = typer;
		postProcessors = new ArrayList<>();
	}

	protected abstract T extractRule(ObjectParser parser);

	protected abstract IType createThisClass();

	protected abstract List<ImportClassContext> getImports();

	protected String getPackage() {
		return null;
	}

	protected abstract void insertImport(String className);

	protected abstract void finish();

	protected IType getThisClass() {
		return tracker.getThisClass();
	}

	private void initialize(String source) {
		logger.debug("Translating LX-source\n{}", source);
		ObjectParser parser = getParser(source, s -> new ObjectLexer(s), s -> new ObjectParser(s));

		// parse the input and make sure it is error-free.
		rule = extractRule(parser);
		tracker = new TypeConstructor(typer, this::insertImport);
		// add all imports + package (which comes first)
		String packageName = getPackage();
		if (packageName != null) {
			tracker.addImport(packageName + ".*", false);
		}
		getImports().forEach(i -> tracker.addImport(i.packageName().getText(), false));

		rewriter = new TokenStreamRewriter(parser.getTokenStream());
		IType thisClass = createThisClass();
		thisClass.setSource(source);
		checkErrors(parser, "Cannot translate class '" + thisClass.getFullName() + "' to Java", ParseError.class);

		// customized post-processing
		postProcessors.forEach(p -> p.accept(thisClass));

		// initialise the infrastructure
		tracker.setThisClass(thisClass);

		// remove all comments
		CommentRemover.remove(rule, rewriter);
	}

	public void addPostProcessor(Consumer<IType> postProcessor) {
		postProcessors.add(postProcessor);
	}
}
