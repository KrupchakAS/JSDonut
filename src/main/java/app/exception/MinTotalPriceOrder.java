package app.exception;

public class MinTotalPriceOrder extends RuntimeException {
    public MinTotalPriceOrder() {
    }

    public MinTotalPriceOrder(String message) {
        super(message);
    }
}
