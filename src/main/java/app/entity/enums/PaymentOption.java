package app.entity.enums;

public enum PaymentOption {

    CASH((byte) 1),
    CARD((byte) 2);

    public final Byte value;

    public Byte getValue() {
        return value;
    }

    PaymentOption(final Byte value) {
        this.value = value;
    }

    public static PaymentOption valueOf(final Byte value) {
        for (PaymentOption status : PaymentOption.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
