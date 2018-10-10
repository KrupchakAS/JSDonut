package app.exception;

public class MinPriceException extends RuntimeException{
    public MinPriceException() {
    }

    public MinPriceException(String message) {
        super(message);
    }
}
