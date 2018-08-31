package app.entity.enums;

public enum PaymentStatus {
    NOT_PAID(1),
    PAID(2);

    public final int value;

    PaymentStatus(final int value){
        this.value=value;
    }
}
