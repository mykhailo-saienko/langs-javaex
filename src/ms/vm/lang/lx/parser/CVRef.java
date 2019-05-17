package ms.vm.lang.lx.parser;

import java.util.function.Function;

import ms.ipp.iterable.tree.SyntheticTree;
import ms.lang.types.ClassVariable;
import ms.lang.types.IType;
import ms.lang.types.Ref;

public class CVRef extends SyntheticTree<CVRef> implements Ref<ClassVariable> {
	private final ClassVariable cv;

	public CVRef(IType type, Function<String, IType> typer) {
		super(name -> new CVRef(type.getVariable(name, v -> v.isStatic()), typer), CVRef.class);
		this.cv = null;
	}

	public CVRef(ClassVariable var, Function<String, IType> typer) {
		super(name -> {
			IType type = typer.apply(var.getTypeName().getName());
			return (type != null) ? new CVRef(type.getVariable(name, null), typer) : null;
		}, CVRef.class);
		this.cv = var;
	}

	@Override
	public ClassVariable getValue() {
		return cv;
	}
}