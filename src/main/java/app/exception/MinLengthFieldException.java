package app.exception;

public class MinLengthFieldException extends RuntimeException {
    public MinLengthFieldException() {
    }

    public MinLengthFieldException(String message) {
        super(message);
    }
}
