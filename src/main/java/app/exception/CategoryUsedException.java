package app.exception;

public class CategoryUsedException extends RuntimeException {
    public CategoryUsedException() {
    }

    public CategoryUsedException(String message) {
        super(message);
    }
}
