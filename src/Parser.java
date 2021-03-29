import java.util.Collections;
import java.util.LinkedList;



public class Parser {
    LinkedList<Token> tokens;
    Token lookahead;

    public ExpressionNode parse(LinkedList<Token> tokens) {
        this.tokens = new LinkedList(tokens);

        lookahead = this.tokens.getFirst();

        ExpressionNode expression = expression();

        if (lookahead.token != Token.EPSILON) {
            throw new ParserException(" %s UnrecognizedToken", lookahead);
        }
        return expression;
    }

    /**
     * une expression peut être interprétée comme une somme d'un ou plusieurs termes. Le premier terme peut commencer par un plus ou un moins.
     * @return
     */
    private ExpressionNode expression() {

        ExpressionNode expression = termeSigné();
        return sommeDeTermes(expression);
    }

    /**
     * une somme de terme .. commence par un terme puis une somme de terme
     */
    private ExpressionNode sommeDeTermes(ExpressionNode expression) {

        if (lookahead.token == Token.PLUSMINUS) {
            AdditionExpressionNode sum;
            if (expression.getType() == ExpressionNode.ADDITION_NODE)
                sum = (AdditionExpressionNode)expression;
            else
                sum = new AdditionExpressionNode(expression, true);

            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = terme();
            sum.add(t, positive);

            return sommeDeTermes(sum);
        }

        return expression;
    }

    /**
     * un terme signé commence par un plus ou un moins puis un terme
     * @return
     */
    private ExpressionNode termeSigné() {

        if (lookahead.token == Token.PLUSMINUS) {
            // l'expression d'un terme signé, c'est la suite de Token : PLUSMINUS;termeSigné
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode terme = terme();
            if (positive){
                return terme;
            } else {
                return  new AdditionExpressionNode( terme, false);
            }
        }

        // ou un simple Token  -> terme
        return terme();
    }

    private ExpressionNode terme()
    {
        // term -> facteur sommeDeTermes
        ExpressionNode facteur = facteur();
        return suiteDeFacteur(facteur);
    }

    private ExpressionNode suiteDeFacteur(ExpressionNode facteur)
    {
        if (lookahead.token == Token.MULTDIV)
        {
            // suite de facteurs -> MULTDIV ou suite de facteurs
            MultiplicationExpressionNode produit;

            if ( expression().getType() == ExpressionNode.MULTIPLICATION_NODE)  {
                produit = (MultiplicationExpressionNode) expression();
            } else {
                produit = new MultiplicationExpressionNode( expression(), true);
            }

            boolean isMultiply = lookahead.sequence.equals("*");
            nextToken();
            ExpressionNode node = facteurSigne();
            produit.add(node, isMultiply);
            suiteDeFacteur(facteur);

            return  suiteDeFacteur(produit);
        } else {
            // fin
        }
        return facteur;
    }

    private ExpressionNode facteurSigne()
    {
        if (lookahead.token == Token.PLUSMINUS)
        {
            // facteurs signés -> PLUSMINUS factor
            boolean isPositive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode facteur = facteur();
            if (isPositive) {
                return facteur;
            } else {
                return new AdditionExpressionNode( facteur, false);
            }
        }
        else
        {
            // facteur Signé -> factor
            return facteur();
        }
    }

    private ExpressionNode facteursMultiples(ExpressionNode expression) {

        // fin
        return expression;
    }

    private ExpressionNode facteur()
    {
        // factor -> argument
        ExpressionNode a = argument();
        return facteursMultiples(a);
    }

    private ExpressionNode argument() {
        if (lookahead.token == Token.OPEN_BRACKET) {
            // argument -> OPEN_BRACKET somme CLOSE_BRACKET
            nextToken();
            ExpressionNode expression = expression();

            if (lookahead.token != Token.CLOSE_BRACKET) {
                throw new ParserException("On attend des parenthese fermantes"
                        + lookahead.sequence + " found instead");
            }

            nextToken();
            return expression;
        } else {
            // argument -> value
            value();
        }
        return null;
    }

    private ExpressionNode value()
    {
        if (lookahead.token == Token.NUMBER)
        {
            // argument -> NUMBER
            ExpressionNode expression = new intNumberNode(lookahead.sequence);
            nextToken();
            return expression;
        } else {

            // si fin
            throw new ParserException("Unexpected symbol "+lookahead.sequence+" found");
        }
    }


    private void nextToken()
    {
        tokens.pop();
        // à la fin de l'input je renvoie -1;
        if (tokens.isEmpty())
            lookahead = new Token(Token.EPSILON, "", -1);
        else
            lookahead = tokens.getFirst();
    }
}
