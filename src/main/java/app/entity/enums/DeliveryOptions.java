package app.entity.enums;

public enum  DeliveryOptions {
    PICKUP((byte)1),
    DELIVERY((byte)2);

    public final Byte value;

    DeliveryOptions (final Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public DeliveryOptions valueOf(final Byte value) {
        for (DeliveryOptions status : DeliveryOptions.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
