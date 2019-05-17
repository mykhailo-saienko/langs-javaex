package ms.vm.lang.javaex;

import static java.nio.charset.Charset.defaultCharset;
import static java.util.Arrays.asList;
import static ms.ipp.Iterables.map;
import static ms.utils.DateHelper.getLocale;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms.lang.types.IType;
import ms.parser.error.Error;
import ms.parser.error.ParseError;
import ms.utils.IOHelper;

/**
 * Source:
 * https://stackoverflow.com/questions/7989135/is-it-possible-to-programmatically-compile-java-source-code-in-memory-only
 * https://github.com/medallia/javaone2016/blob/master/src/main/java/com/medallia/codegen/SimpleJavaCompiler.java
 * 
 * Source code for JDK:
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/com/sun/tools/javac/api/JavacTool.java?av=f
 * 
 * @author mykhailo.saienko
 *
 */
public class JCompiler {
	private static final Logger logger = LogManager.getLogger(JCompiler.class);

	private static final String COMPILER_CLASS = "com.sun.tools.javac.api.JavacTool";

	private final JavaCompiler compiler;
	private final FileManager manager;

	public JCompiler(String classPath) {
		try {
			// Cobbled together from ToolsProvider.getSystemJavaCompiler()
			// so that the compiler works with only tools.jar being installed
			// (without the entire JDK-suite).
			compiler = Class.forName(COMPILER_CLASS).asSubclass(JavaCompiler.class).newInstance();
		} catch (Exception e) {
			logger.fatal("Compiler cannot be initialised: " + e.getMessage());
			System.exit(1);
			// to signalise the catch-clause doesn't recover the flow
			throw new RuntimeException("Compiler cannot be initialised", e);
		}

		StandardJavaFileManager fm = compiler.getStandardFileManager(null, getLocale(), defaultCharset());
		List<File> classjars = new ArrayList<>();
		try {
			for (String jar : System.getProperty("java.class.path").split(File.pathSeparator)) {
				classjars.add(new File(jar));
			}
			if (classPath != null) {
				File[] files = IOHelper.listFiles(classPath, ".jar");
				logger.debug("Adding jars from '{}': {}", () -> classPath, () -> asList(files));
				for (File userjar : files) {
					classjars.add(userjar);
				}
			}
			logger.info("Setting JCompiler classpath to " + classjars);
			fm.setLocation(StandardLocation.CLASS_PATH, classjars);
		} catch (IOException e) {
			logger.fatal("Cannot set the classpath for Java FileManager " + classjars + ": " + e.getMessage());
			throw new IllegalStateException("Cannot set the classpath for Java FileManager " + classjars, e);
		}
		manager = new FileManager(fm);
	}

	public void compile(String source, String unitName, List<IType> prototypes) {
		logger.trace("Compiling unit '{}' with source\n{}", unitName, source);
		// needed so that the compiler may "see" the new types while compiling
		getManager().registerTypes(prototypes);

		JavaFileObject file = new SourceFile(IOHelper.toUri(unitName), source);
		Iterable<? extends JavaFileObject> compilationUnits = asList(file);
		// -parameters -> include method parameter names
		Iterable<String> opts = asList("-parameters");
		final StringWriter output = new StringWriter();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

		CompilationTask task = compiler.getTask(output, getManager(), diagnostics, opts, null, compilationUnits);
		if (!task.call()) {
			List<Error> errors = map(diagnostics.getDiagnostics(), //
					d -> new Error(null, (int) d.getLineNumber(), (int) d.getColumnNumber(),
							d.getMessage(getLocale())));
			throw new ParseError("Cannot compile Java-source '" + unitName + "'", errors);
		}
		getManager().updateCompiledTypes(prototypes);
	}

	public FileManager getManager() {
		return manager;
	}

	/** Represents a source file whose contents is loaded from a String */
	private static class SourceFile extends SimpleJavaFileObject {
		private final String contents;

		SourceFile(URI uri, String contents) {
			super(uri, Kind.SOURCE);
			this.contents = contents;
		}

		@Override
		public String getName() {
			return uri.getRawSchemeSpecificPart();
		}

		/** Ignore the file name for public classes */
		@Override
		public boolean isNameCompatible(String simpleName, Kind kind) {
			return true;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
			return contents;
		}
	}

}