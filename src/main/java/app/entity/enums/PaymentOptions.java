package app.entity.enums;

public enum PaymentOptions {
    CASH((byte)1),
    CARD((byte)2);

    public final Byte value;

    PaymentOptions (final Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public PaymentOptions valueOf(final Byte value) {
        for (PaymentOptions status : PaymentOptions.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
