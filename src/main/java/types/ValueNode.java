package types;

public class ValueNode {
    private final String value;
    private final ValueNode left;
    private final ValueNode right;

    public ValueNode(String value, ValueNode left, ValueNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public ValueNode getLeft() {
        return left;
    }

    public ValueNode getRight() {
        return right;
    }
}
