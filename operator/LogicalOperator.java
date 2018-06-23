package operator;

public enum LogicalOperator {
	AND("AND"), //
    OR("OR"), //
    NOT("NOT"),
    OPENING_BRACKET("("),
    CLOSING_BRACKET(")");

    private String value;

    private LogicalOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
