public interface ExpressionNode {
    public static final int NUMBER_NODE = 1;
    public static final int ADDITION_NODE = 2;
    public static final int MULTIPLICATION_NODE = 3;
    public int getType();
    public double getValue();
}

