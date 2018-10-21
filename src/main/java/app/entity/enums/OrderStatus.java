package app.entity.enums;

public enum OrderStatus {

    AWAITING_PAYMENT(1),
    AWAITING_SHIPMENT(2),
    SHIPPED(3),
    DELIVERED(4);

    private final Integer value;

    public Integer getValue() {
        return value;
    }

    OrderStatus(final Integer value) {
        this.value = value;
    }

    public static OrderStatus valueOf(final Integer value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

}


