public class Application {

    public static void main(String[] args)  {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("\\(", 1); // open bracket
        tokenizer.add("\\)", 2); // close bracket
        tokenizer.add("[+-]", 3); // plus or minus
        tokenizer.add("[*/]", 4); // mult or divide
        tokenizer.add("[0-9]+",5); // integer number

        try {
            tokenizer.tokenize("1+1");

            for (Token token : tokenizer.getTokens()) {
                System.out.println("" + token.token + " " + token.sequence);
            }
        }
        catch (Tokenizer.ParserException e) {
            System.out.println(e.getMessage());
        }



    }
}
