public class Application {

    public static void main(String[] args)  {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("\\(", 1); // open bracket
        tokenizer.add("\\)", 2); // close bracket
        tokenizer.add("[+-]", 3); // plus or minus
        tokenizer.add("[*/]", 4); // mult or divide
        tokenizer.add("[0-9]+",5); // integer number

        tokenizer.tokenize("1+56*4");

        Parser parser = new Parser();
        try {
            ExpressionNode expression = parser.parse(tokenizer.getTokens());
            System.out.println("La valeur de l'expression est "+expression.getValue());
        }
        catch (ParserException e) {
            System.out.println(e.getMessage());
        }
    }
}
