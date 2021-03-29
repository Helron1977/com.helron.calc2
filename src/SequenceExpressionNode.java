import java.util.LinkedList;

public abstract class SequenceExpressionNode
        implements ExpressionNode {

    protected LinkedList<Terme> terms;

    public SequenceExpressionNode() {
        this.terms = new LinkedList<Terme>();
    }

    public SequenceExpressionNode(ExpressionNode a, boolean positive) {
        this.terms = new LinkedList<Terme>();
        this.terms.add(new Terme(positive, a));
    }

    public void add(ExpressionNode a, boolean positive) {
        this.terms.add(new Terme(positive, a));
    }
}
