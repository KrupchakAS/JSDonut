package app.entity.enums;

public enum DeliveryOption {

    PICKUP((byte) 1),
    DELIVERY((byte) 2);

    public final Byte value;

    public Byte getValue() {
        return value;
    }

    DeliveryOption(final Byte value) {
        this.value = value;
    }

    public static DeliveryOption valueOf(final Byte value) {
        for (DeliveryOption status : DeliveryOption.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
