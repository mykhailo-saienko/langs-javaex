package ms.vm.lang.lx;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import ms.lang.AbstractLS;
import ms.lang.LanguageSystem;
import ms.lang.java.JType;
import ms.lang.java.JavaMethod;
import ms.lang.types.BaseMethod;
import ms.lang.types.IType;
import ms.lang.types.TranslatedType;
import ms.lang.types.TypeName;
import ms.vm.lang.javaex.JavaExLS;
import ms.vm.lang.lx.parser.InitTranslator;
import ms.vm.lang.lx.parser.LXTypeTranslator;

public class LxLS implements LanguageSystem {

	private final Map<String, LXType> lxTypes;
	private final JavaExLS javaLS;

	/**
	 * Re-uses javaEx' compiler and file manager.
	 * 
	 * @param source
	 */
	public LxLS(JavaExLS source) {
		this.javaLS = source;
		lxTypes = new HashMap<>();
	}

	@Override
	public List<IType> compile(String source, Map<String, Object> options) {
		TranslatedType translated = new LXTypeTranslator(this::getType).translate(source);
		LXType result = compile(translated, options, true);
		return Arrays.asList(result);
	}

	private LXType compile(TranslatedType type, Map<String, Object> options, boolean keep) {
		// disables JavaEX' default extraction routine
		options.put(AbstractLS.EXTRACTED_OPTION, Arrays.asList(type.getOriginal()));
		IType compiled = javaLS.compile(type.getSource(), options).get(0);

		LXType result = new LXType(compiled, type.getSource());
		if (keep) {
			lxTypes.put(compiled.getFullName(), result);
		}
		return result;
	}

	@Override
	public boolean deleteType(String typeName) {
		LXType lxType = loadLXType(typeName);
		// cannot delete non-fleeting types and those undeleteable by JavaLS
		if (lxType == null || !lxType.isFleeting() || !javaLS.deleteType(typeName)) {
			return false;
		}
		lxTypes.remove(typeName);
		return true;
	}

	@Override
	public IType getType(List<String> packages, String name, Predicate<IType> pred) {
		return augmentType(javaLS.getType(packages, name, pred));
	}

	@Override
	public boolean isAssignableFrom(IType base, IType target) {
		return javaLS.isAssignableFrom(base, target);
	}

	@Override
	public Object eval(String source, TypeName type) {
		// TODO: Integrate "initialize" here (as it is clearly an
		// LX-class-initialization routine)
		return javaLS.eval(source, type);
	}

	public Object initialize(String initRoutine) throws InvocationTargetException {
		String className = "Init";
		String staticMethod = "init";
		Map<String, Object> options = new HashMap<>();
		TranslatedType translated = new InitTranslator(this::getType, className, staticMethod).translate(initRoutine);
		IType result = compile(translated, options, false);
		BaseMethod init = result.getStaticMethod(staticMethod);
		return init.invoke();
	}

	private LXType loadLXType(String fullName) {
		return lxTypes.get(fullName);
	}

	@Override
	public boolean isSupported(BaseMethod method) {
		// TODO: We need a special LXMethod which incorporates default
		// parameters (remove them from JavaMethod)
		return method instanceof JavaMethod;
	}

	@Override
	public boolean isSupported(IType type) {
		return isNative(type) || type instanceof JType;
	}

	@Override
	public boolean isNative(IType type) {
		return type instanceof LXType;
	}

	@Override
	public void forEachType(String packageName, Predicate<IType> pred, Consumer<IType> processor) {
		javaLS.forEachType(packageName, pred, t -> processor.accept(augmentType(t)));
	}

	@Override
	public void forEachUserType(String packageName, Predicate<IType> pred, Consumer<IType> processor) {
		javaLS.forEachUserType(packageName, pred, t -> processor.accept(augmentType(t)));
	}

	/**
	 * Replaces java-types known to LxLS with their augmented LX-versions.
	 * 
	 * @param inheritedTypes
	 * @return
	 */
	private IType augmentType(IType type) {
		if (type == null) {
			return null;
		}
		LXType lxType = lxTypes.get(type.getFullName());
		return lxType == null ? type : lxType;
	}

}
