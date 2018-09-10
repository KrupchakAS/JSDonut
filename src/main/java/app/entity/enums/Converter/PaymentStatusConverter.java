package app.entity.enums.Converter;

import app.entity.enums.PaymentStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, Byte> {

    @Override
    public Byte convertToDatabaseColumn(PaymentStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Byte dbData) {
        return PaymentStatus.valueOf(dbData);
    }
}

