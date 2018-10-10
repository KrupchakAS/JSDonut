package app.exception;

public class MinTotalPriceOrderException extends RuntimeException {
    public MinTotalPriceOrderException() {
    }

    public MinTotalPriceOrderException(String message) {
        super(message);
    }
}
