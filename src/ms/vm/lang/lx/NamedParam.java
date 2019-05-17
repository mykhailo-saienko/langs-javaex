package ms.vm.lang.lx;

public class NamedParam {

	private final String id;
	private final String assignment;
	private final boolean rewriteDefault;
	private final String expression;

	public NamedParam(String id, String assignment, boolean rewriteDefault, String expression) {
		this.id = id;
		this.assignment = assignment;
		this.rewriteDefault = rewriteDefault;
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public boolean isRewriteDefault() {
		return rewriteDefault;
	}

	public String getAssignment() {
		return assignment;
	}

	public String getId() {
		return id;
	}
}
