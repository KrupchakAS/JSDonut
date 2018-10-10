package app.exception;

public class ObjectAlreadyInOrderException extends RuntimeException {
    public ObjectAlreadyInOrderException() { super(); }

    public ObjectAlreadyInOrderException(String message) { super(message); }
}
