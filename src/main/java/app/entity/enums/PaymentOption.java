package app.entity.enums;

public enum PaymentOption {

    CASH(1),
    CARD(2);

    public final Integer value;

    public Integer getValue() {
        return value;
    }

    PaymentOption(final Integer value) {
        this.value = value;
    }

    public static PaymentOption valueOf(final Integer value) {
        for (PaymentOption status : PaymentOption.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
