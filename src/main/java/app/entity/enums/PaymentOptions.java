package app.entity.enums;

public enum PaymentOptions {
    CASH(1),
    CARD(2);

    public final int value;

    PaymentOptions(final int value){
        this.value=value;
    }
}
