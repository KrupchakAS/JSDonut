package app.exception;

public class MinValueException extends RuntimeException{
    public MinValueException() {
    }

    public MinValueException(String message) {
        super(message);
    }
}
