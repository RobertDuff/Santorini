package duff.games.santorini.model;

public class SantoriniException extends Exception
{
    private static final long serialVersionUID = 1L;

    public SantoriniException ()
    {
    }

    public SantoriniException ( String message )
    {
        super ( message );
    }

    public SantoriniException ( Throwable cause )
    {
        super ( cause );
    }

    public SantoriniException ( String message, Throwable cause )
    {
        super ( message, cause );
    }

    public SantoriniException ( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace )
    {
        super ( message, cause, enableSuppression, writableStackTrace );
    }

}
