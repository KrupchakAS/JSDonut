package app.entity.enums;

public enum  OrderStatus {
    AWAITING_PAYMENT(1),
    AWAITING_SHIPMENT(2),
    SHIPPED(3),
    DELIVERED(4);

    public final int value;

    OrderStatus(final int value){
        this.value=value;
    }
}
