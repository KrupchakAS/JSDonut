package app.entity.enums;

public enum PaymentStatus {
    NOT_PAID((byte)1),
    PAID((byte)2);

    public final Byte value;

    PaymentStatus (final Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public PaymentStatus valueOf(final Byte value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
