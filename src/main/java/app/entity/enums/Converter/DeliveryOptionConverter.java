package app.entity.enums.Converter;

import app.entity.enums.DeliveryOption;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DeliveryOptionConverter implements AttributeConverter<DeliveryOption, Byte> {

    @Override
    public Byte convertToDatabaseColumn(DeliveryOption attribute) {
        return attribute.getValue();
    }

    @Override
    public DeliveryOption convertToEntityAttribute(Byte dbData) {
        return DeliveryOption.valueOf(dbData);
    }

}
