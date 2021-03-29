public interface ExpressionNode {
    public static final int NUMBER_NODE = 5;
    public static final int ADDITION_NODE = 3;
    public static final int MULTIPLICATION_NODE = 4;
    public int getType();
    public double getValue();
}

