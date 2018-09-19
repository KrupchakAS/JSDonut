package app.exception;

public class ObjectAlreadyInOrder extends RuntimeException {
    public ObjectAlreadyInOrder() { super(); }

    public ObjectAlreadyInOrder(String message) { super(message); }
}
