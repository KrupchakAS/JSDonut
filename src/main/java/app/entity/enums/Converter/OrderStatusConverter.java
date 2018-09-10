package app.entity.enums.Converter;

import app.entity.enums.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Byte> {

    @Override
    public Byte convertToDatabaseColumn(OrderStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Byte dbData) {
        return OrderStatus.valueOf(dbData);
    }
}

