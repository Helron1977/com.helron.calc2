public class ParserException extends RuntimeException
{



    private Token token = null;


    public ParserException(String message)
    {
        super(message);
    }


    public ParserException(String message, Token token)
    {
        super(message);
        this.token = token;
    }


    public Token getToken()
    {
        return token;
    }


    public String getMessage()
    {
        String msg = super.getMessage();
        if (token != null)
        {
            msg = msg.replace("%s", token.sequence);
        }
        return msg;
    }

}
