package ms.vm.lang.lx.parser;

import static java.util.Arrays.asList;
import static ms.ipp.Algorithms.and;
import static ms.ipp.Iterables.mapFilter;
import static ms.lang.types.Mod.PUBLIC;
import static ms.lang.types.VarModifier.STANDARD;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms.ipp.iterable.tree.path.StdPathManipulator;
import ms.lang.AbstractContainer;
import ms.lang.types.BaseMethod;
import ms.lang.types.ClassVariable;
import ms.lang.types.IType;
import ms.lang.types.TypeName;
import ms.lang.types.Variable;

public class TypeConstructor extends AbstractContainer<CVRef, ClassVariable, IType> {
	private static final Logger logger = LogManager.getLogger();

	private IType thisClass;
	private final BiFunction<List<String>, String, IType> typer;

	private final List<String> packages;
	private final Consumer<String> importer;

	public TypeConstructor(BiFunction<List<String>, String, IType> typer, Consumer<String> importer) {
		super(CVRef.class);

		this.typer = typer;
		this.importer = importer;
		packages = new ArrayList<>();
		this.thisClass = null;

		addHighest(this::specialLookup);
	}

	@Override
	public void setGlobal(String name, ClassVariable var) {
		super.setGlobal(name, var);
		// AbstractContainer's part sets the variable on the stack where it can
		// be found by its name while the VarTracker's functionality
		// allows us to found the variable via "this.<varName>" (which takes
		// precedence over any other entity in the Container.
		getThisClass().addVariable(var, null);
	}

	private CVRef specialLookup(String id) {
		if (thisClass == null) {
			throw new IllegalArgumentException("VarTracker is not initialized");
		}
		ClassVariable result = null;
		if (id == "this") { // this is considered initialised
			result = new ClassVariable(PUBLIC, STANDARD, false, thisClass.getType(), "this", "");
		} else if (id == "super") {
			TypeName base = thisClass.getBase();
			if (base == null) {
				throw new IllegalArgumentException("Error while looking for " + id + ". Class " + thisClass.getType()
						+ " has no well-defined super class");
			}
			result = new ClassVariable(PUBLIC, STANDARD, false, base, "super", null);
		}
		return result == null ? null : createInstRef(result);
	}

	@Override
	protected CVRef createTypeRef(IType type) {
		return new CVRef(type, this::getType);
	}

	@Override
	protected CVRef createInstRef(ClassVariable cv) {
		return new CVRef(cv, this::getType);
	}

	/**
	 * Returns a list of those references directly referred to in a given rule
	 * context which satisfy a given predicate.<br>
	 * Also verifies that all mentioned references exist.
	 * 
	 * @param ctx
	 * @param pred
	 * @return
	 */
	public List<Variable> getVariables(ParseTree ctx, boolean collectMethods, Predicate<Variable> pred) {
		if (ctx == null) {
			return new ArrayList<>();
		}
		Set<String> dependencies = DependencyCollector.collect(ctx, collectMethods);

		// some ids, such as class references in static method calls, must be
		// filtered out. We do this by replacing them with nulls in the
		// map-phase and filtering the nulls out in the filter phase.
		return mapFilter(dependencies, d -> {
			if ("super".equals(d)) {
				throw new IllegalArgumentException("'super' is not a valid identifier in this context");
			}
			Variable var = get(d);
			// neither a var nor a class in a static method call.
			if (var == null && getType(d) == null) {
				throw new IllegalArgumentException("Variable '" + d + "' is not found on the stack");
			}
			return var;
		}, and(v -> v != null, pred));
	}

	public BaseMethod getMethod(String fullName, Predicate<BaseMethod> pred) {
		List<String> ids = StdPathManipulator.fromPath(fullName);
		if (ids.isEmpty()) {
			throw new IllegalArgumentException("Id cannot be empty");
		}
		String methodName = ids.get(ids.size() - 1);
		List<String> header = ids.size() > 1 ? ids.subList(0, ids.size() - 1) : asList("this");

		ClassVariable var = get(StdPathManipulator.toPath(header));
		if (var != null) {
			logger.trace("Var for method: {} is {}", fullName, var);
			IType type = getType(var.getTypeName().getName());
			return (type != null) ? type.getMethod(methodName, pred) : null;
		}
		// if it is not a variable -> only static methods
		IType type = getType(StdPathManipulator.toPath(header));
		logger.trace("Type for method: {} is {}", fullName, type);
		return type == null ? null : type.getMethod(methodName, and(pred, m -> m.isStatic()));

	}

	/**
	 * Adds reference packages/ which may be consulted while looking up a class
	 * definition. The order in which packages are added is maintained
	 * 
	 * @param className
	 * @param addToSource
	 */
	public void addImport(String className, boolean addToSource) {
		// insert only if not added before.
		if (className != null && !packages.contains(className)) {
			packages.add(className);
			if (importer != null && addToSource) {
				importer.accept(className);
			}
		}
	}

	public void setThisClass(IType thisClass) {
		this.thisClass = thisClass;
	}

	public IType getThisClass() {
		return thisClass;
	}

	public IType getType(List<String> prefixes, String typeName) {
		if (getThisClass() != null
				&& (getThisClass().getFullName().equals(typeName) || getThisClass().getSimpleName().equals(typeName))) {
			return getThisClass();
		}
		return typer.apply(prefixes, typeName);
	}

	@Override
	public IType getType(String typeName) {
		return getType(packages, typeName);
	}

}
