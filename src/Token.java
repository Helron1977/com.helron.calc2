
public class Token {
    public static final int EPSILON = 0;
    public static final int PLUSMINUS = 1;
    public static final int MULTDIV = 2;
    public static final int OPEN_BRACKET = 3;
    public static final int CLOSE_BRACKET = 4;
    public static final int NUMBER = 5;


    public final int token;
    public final String sequence;

    public Token(int token, String sequence) {
        super();
        this.token = token;
        this.sequence = sequence;
    }

    public Token(int token, String sequence, int i) {
        this.token = token;
        this.sequence = sequence;
    }
}
