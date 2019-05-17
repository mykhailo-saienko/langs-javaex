package ms.vm.lang.javaex;

import static javax.tools.JavaFileObject.Kind.CLASS;
import static javax.tools.StandardLocation.CLASS_PATH;
import static ms.ipp.Iterables.asSet;
import static ms.ipp.Iterables.filterMap;
import static ms.ipp.Iterables.getInsert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms.lang.java.JType;
import ms.lang.types.IType;
import ms.utils.IOHelper;

/** A simple file manager that collects written files in memory */
public class FileManager extends ForwardingJavaFileManager<JavaFileManager> {

	private static final Logger logger = LogManager.getLogger();

	private final Map<String, ClassFile> files;
	private final Map<Location, ClassLoader> loaders;

	private Predicate<IType> isDeletable;

	public FileManager(JavaFileManager target) {
		super(target);
		files = new HashMap<>();
		loaders = new HashMap<>();
		setIsDeletable(null);
	}

	public void registerTypes(List<IType> prototypes) {
		for (int i = 0; i < prototypes.size(); ++i) {
			JType jType = registerJavaType(prototypes.get(i));
			logger.debug("Registered class with name '{}'", jType.getFullName());
			prototypes.set(i, jType);
		}
	}

	private JType registerJavaType(IType type) {
		JType jType = new JType(type);
		ClassFile file = new ClassFile(jType);
		files.put(file.getName(), file);
		return jType;
	}

	public boolean unregisterType(String typeName) {
		if (typeName == null) {
			return false;
		}

		ClassFile cf = files.get(typeName);
		if (cf == null) {
			return false;
		}
		JType type = cf.getType();
		// TODO: Non-fleeting customary classes are only possible in LX. We
		// should consider this while refactoring LX-functionality
		if (isDeletable != null && !isDeletable.test(type)) {
			return false;
		}
		if (!type.isFleeting()) {
			logger.debug("Cannot delete non-fleeting class '{}'", type.getFullName());
			return false;
		}
		files.remove(typeName);

		logger.debug("Unregistered class with name '{}'", typeName);
		return true;
	}

	@Override
	public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind, FileObject sibling) {
		return files.get(className);
	}

	@Override
	public ClassLoader getClassLoader(Location location) {
		return getInsert(location, loaders, () -> createClassLoader(location));
	}

	@Override
	public String inferBinaryName(Location location, JavaFileObject file) {
		String name = null;
		ClassFile cf = files.get(file.getName());
		if (location == StandardLocation.CLASS_PATH && cf != null) {
			name = cf.getName();
			/*
			 * System.out.println( "Inferred binary name for location " + location +
			 * " and jfo " + file + " is '" + name + "'");
			 */
		} else {
			name = super.inferBinaryName(location, file);
		}
		return name;
	};

	// https://stackoverflow.com/questions/5819376/java-compiler-api-classloader?rq=1
	// Needed for the compiler to find the in-memory classes.
	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<Kind> kinds, boolean recurse)
			throws IOException {
		List<JavaFileObject> out = new ArrayList<>();
		for (JavaFileObject o : fileManager.list(location, packageName, kinds, recurse)) {
			out.add(o);
		}

		if (location == StandardLocation.CLASS_PATH && kinds.contains(Kind.CLASS)) {
			List<ClassFile> filterMap = filterMap(files.entrySet(), e -> e.getKey().startsWith(packageName),
					e -> e.getValue());
			out.addAll(filterMap);
		}

		if (out != null && !out.isEmpty() && !packageName.startsWith("java")) {
			logger.trace("Parameters: location={}, package name={}, kinds={}, recurse={}\nResult is: {}", location,
					packageName, kinds, recurse, out);
		}

		return out;
	}

	public void forEachType(String packageName, Function<String, ? extends IType> converter,
			Consumer<IType> processor) {
		try {
			Iterable<JavaFileObject> list = list(CLASS_PATH, packageName, asSet(CLASS), true);
			for (JavaFileObject jfo : list) {
				String name = inferBinaryName(CLASS_PATH, jfo);
				IType type = converter.apply(name);
				logger.debug("Type with name '{}' is {}", name, type);
				if (type != null) {
					processor.accept(type);
				}
			}
		} catch (IOException e) {
			logger.fatal("Unexpected exception encountered", e);
		}
	}

	public void forEachUserType(String packageName, Predicate<? super JType> pred, Consumer<? super JType> processor) {
		for (ClassFile cf : files.values()) {
			JType type = cf.getType();
			if (type.getFullName().startsWith(packageName) && (pred == null || pred.test(type))) {
				processor.accept(type);
			}
		}
	}

	private ClassLoader createClassLoader(Location location) {
		return new ClassLoader(super.getClassLoader(location)) {
			@Override
			protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
				Class<?> result = super.loadClass(name, resolve);
				logger.debug("Found: " + name + ". It is " + result);
				return result;
			}

			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				ClassFile cf = files.get(name);
				if (cf == null) {
					logger.trace("Trying to find standard class {}", name);
					try {
						Class<?> clazz = super.findClass(name);
						logger.trace("Result: {}", clazz);
						return clazz;
					} catch (ClassNotFoundException e) {
						logger.trace("Could not find class '{}'", name);
						throw e;
					}
				}
				if (logger.isDebugEnabled()) {
					logger.debug("Trying to find {}. User-defined type is {}. Output is {}", name, cf, files);
				}

				JType jtype = cf.getType();
				if (jtype.getCompiled() == null) {
					Class<?> jClass = defineClass(name, cf.getByteCode(), 0, cf.getByteCode().length);
					jtype.setCompiled(jClass);
				}
				return jtype.getCompiled();
			}
		};
	}

	IType loadUserClass(String className) {
		ClassFile cf = files.get(className);
		return (cf != null) ? cf.getType() : null;
	}

	IType loadJavaType(String className) {
		Class<?> jClass = doLoadClass(className);
		return jClass == null ? null : new JType(jClass);
	}

	public void updateCompiledTypes(List<IType> types) {
		// this calls manager's findClass which finishes up the initisation
		// NOTE: the given 'types' contains refs to the original types which
		// will be updated in findClass, that's why we don't modify it here
		types.forEach(t -> doLoadClass(t.getFullName()));
	}

	private Class<?> doLoadClass(String name) {
		try {
			// loading a class will not initialize it (static initers are not
			// run). Class.forName does run the initers but uses the caller's
			// class loader.
			// TODO: Can we somehow initialize the class after calling loadClass
			Class<?> jClass = getClassLoader(CLASS_PATH).loadClass(name);

			// we have encountered a murky class (such as anonymous inner class)
			if (jClass == null || jClass.getCanonicalName() == null) {
				return null;
			}
			return jClass;
		} catch (ClassNotFoundException | NoClassDefFoundError e) {
			return null;
		}
	}

	public void setIsDeletable(Predicate<IType> preDeleteChecker) {
		this.isDeletable = preDeleteChecker;
	}

	/**
	 * A compiled class file. Its content is stored in a ByteArrayOutputStream.
	 */
	private static class ClassFile extends SimpleJavaFileObject {
		private ByteArrayOutputStream outputStream;
		private final JType type;

		public ClassFile(JType type) {
			super(IOHelper.toUri(type.getFullName()), Kind.CLASS);
			this.type = type;

			outputStream = null;
		}

		@Override
		public String getName() {
			return uri.getRawSchemeSpecificPart();
		}

		@Override
		public OutputStream openOutputStream() throws IOException {
			return outputStream = new ByteArrayOutputStream();
		}

		@Override
		public InputStream openInputStream() throws IOException {
			return new ByteArrayInputStream(getByteCode());
		}

		@Override
		public String toString() {
			return toUri().toString() + "@Memory";
		}

		public JType getType() {
			return type;
		}

		byte[] getByteCode() {
			return outputStream.toByteArray();
		}
	}

}