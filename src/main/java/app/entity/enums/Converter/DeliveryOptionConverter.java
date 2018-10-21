package app.entity.enums.Converter;

import app.entity.enums.DeliveryOption;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DeliveryOptionConverter implements AttributeConverter<DeliveryOption, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DeliveryOption attribute) {
        return attribute.getValue();
    }

    @Override
    public DeliveryOption convertToEntityAttribute(Integer dbData) {
        return DeliveryOption.valueOf(dbData);
    }

}
