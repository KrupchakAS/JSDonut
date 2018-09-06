package app.entity.enums;

public enum PaymentOptions {
    CASH(1),
    CARD(2);

    private final int value;

    PaymentOptions(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
