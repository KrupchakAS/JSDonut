package app.exception;

public class ObjectUsedException extends RuntimeException {
    public ObjectUsedException() {
    }

    public ObjectUsedException(String message) {
        super(message);
    }
}
