package app.entity.enums;

public enum OrderStatus {

    AWAITING_PAYMENT((byte) 1),
    AWAITING_SHIPMENT((byte) 2),
    SHIPPED((byte) 3),
    DELIVERED((byte) 4);

    private final Byte value;

    public Byte getValue() {
        return value;
    }

    OrderStatus(final Byte value) {
        this.value = value;
    }

    public static OrderStatus valueOf(final Byte value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}


