package app.exception;

public class MinQuantityException extends RuntimeException {
    public MinQuantityException() {
    }

    public MinQuantityException(String message) {
        super(message);
    }
}
