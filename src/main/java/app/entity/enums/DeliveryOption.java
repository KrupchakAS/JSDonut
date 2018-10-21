package app.entity.enums;

public enum DeliveryOption {

    PICKUP(1),
    DELIVERY(2);

    public final Integer value;

    public Integer getValue() {
        return value;
    }

    DeliveryOption(final Integer value) {
        this.value = value;
    }

    public static DeliveryOption valueOf(final Integer value) {
        for (DeliveryOption status : DeliveryOption.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
