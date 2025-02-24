package util.exception;

public class NicExcepition extends Exception{
    private int statusCode;;

    public NicExcepition() {
        super();
    }

    public NicExcepition(String message, int statusCode ) {
        super(message);
        this.statusCode = statusCode;

    }
}
