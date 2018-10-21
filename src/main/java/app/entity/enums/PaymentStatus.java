package app.entity.enums;

public enum PaymentStatus {

    NOT_PAID(1),
    PAID(2);

    public final Integer value;

    public Integer getValue() {
        return value;
    }

    PaymentStatus(final Integer value) {
        this.value = value;
    }

    public static PaymentStatus valueOf(final Integer value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

}
