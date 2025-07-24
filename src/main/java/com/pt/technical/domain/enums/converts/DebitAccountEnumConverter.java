package com.pt.technical.domain.enums.converts;

import com.pt.technical.domain.enums.DebitAccountEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class DebitAccountEnumConverter implements AttributeConverter<DebitAccountEnum, String> {

    @Override
    public String convertToDatabaseColumn(DebitAccountEnum attribute) {
        return attribute != null ? attribute.getStatus() : null;
    }

    @Override
    public DebitAccountEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        for (DebitAccountEnum status : DebitAccountEnum.values()) {
            if (status.getStatus().equalsIgnoreCase(dbData)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + dbData);
    }
}
