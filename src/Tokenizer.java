import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer(LinkedList<TokenInfo> tokenInfos) {
        this.tokenInfos = tokenInfos;
    }
    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void tokenize(String str) throws ParserException {
        String s = new String(str);
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;

                    String tokenString = m.group().trim();
                    tokens.add(new Token(info.token, tokenString));

                    s = m.replaceFirst("").trim();
                    break;
                }
            }
            if (!match){
                throw new ParserException("UnTokenable char in input: " + s);
            }
        }
    }

    public void add(String regex, int token) {
        tokenInfos.add(
                new TokenInfo(
                        Pattern.compile("^("+regex+")"), token));
    }


    public LinkedList<Token> getTokens() {
        return tokens;
    }

}
