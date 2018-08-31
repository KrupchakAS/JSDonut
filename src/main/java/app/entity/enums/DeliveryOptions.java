package app.entity.enums;

public enum  DeliveryOptions {
    PICKUP(1),
    DELIVERY(2);

    //MyEnum e = MyEnum.Test1;
    //int value = e.value; // = 1

    public final int value;

    DeliveryOptions(final int value){
        this.value=value;
    }
}
