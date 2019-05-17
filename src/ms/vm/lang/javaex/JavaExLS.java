package ms.vm.lang.javaex;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import ms.ipp.Iterables;
import ms.lang.java.JavaLS;
import ms.lang.types.IType;
import ms.lang.types.Instance;

public class JavaExLS extends JavaLS {
	private final JCompiler compiler;

	public JavaExLS(String classPath, Function<String, Instance> refResolver) {
		super(refResolver);
		this.compiler = new JCompiler(classPath);
	}

	private JavaExLS(JCompiler compiler, Function<String, Instance> refResolver) {
		super(refResolver);
		this.compiler = compiler;
	}

	public FileManager getManager() {
		return compiler.getManager();
	}

	@Override
	protected List<IType> extract(String source, Map<String, Object> options) {
		JavaTypeExtractor extractor = new JavaTypeExtractor(source);
		options.put("CompileUnitName", extractor.getPublic());
		return extractor.getPrototypes();
	}

	@Override
	protected void compile(String source, List<IType> types, Map<String, Object> options) {
		String compileUnitName = Iterables.get("CompileUnitName", options);
		compiler.compile(source, compileUnitName, types);
	}

	@Override
	public void forEachUserType(String packageName, Predicate<IType> pred, Consumer<IType> processor) {
		getManager().forEachUserType(packageName, pred, processor);
	}

	@Override
	public void forEachType(String packageName, Predicate<IType> pred, Consumer<IType> processor) {
		Function<String, IType> retriever = name -> getType(Arrays.asList(packageName), name, pred);
		getManager().forEachType(packageName, retriever, processor);
	}

	@Override
	public boolean deleteType(String typeName) {
		return getManager().unregisterType(typeName);

	}

	/**
	 * Standard Java-LS uses Class.forName which cannot accommodate for the custom
	 * class loader in FileManager. Besides, we have to insert the
	 * user-class-loading routine between the primitive and the java class loading
	 * system (to prioritize the user-defined classes). Hence, we have to re-write
	 * the entire type loading system.
	 */
	@Override
	protected List<TypeLoader> initTypeLoaders() {
		return Arrays.asList(new TypeLoader(this::loadPrimitive, TypeLoader::noPackage), //
				new TypeLoader(getManager()::loadUserClass), //
				new TypeLoader(getManager()::loadJavaType, p -> TypeLoader.add(p, asList("java.lang.*"))));
	}
}
