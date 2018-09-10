package app.entity.enums.Converter;


import app.entity.enums.PaymentOption;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentOptionConverter implements AttributeConverter<PaymentOption, Byte> {

    @Override
    public Byte convertToDatabaseColumn(PaymentOption attribute) {
        return attribute.getValue();
    }

    @Override
    public PaymentOption convertToEntityAttribute(Byte dbData) {
        return PaymentOption.valueOf(dbData);
    }
}

