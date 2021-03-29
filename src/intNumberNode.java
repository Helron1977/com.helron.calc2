public class intNumberNode implements ExpressionNode {
    private final double value;

    public intNumberNode(double value) {
        this.value = value;
    }

    public intNumberNode(String value) {
        this.value = Double.parseDouble(value);
    }

    public double getValue() {
        return value;
    }

    public int getType() {
        return ExpressionNode.NUMBER_NODE;
    }
}
