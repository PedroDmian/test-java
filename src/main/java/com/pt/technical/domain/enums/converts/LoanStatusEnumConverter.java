package com.pt.technical.domain.enums.converts;

import com.pt.technical.domain.enums.LoanStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class LoanStatusEnumConverter implements AttributeConverter<LoanStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(LoanStatusEnum attribute) {
        return attribute != null ? attribute.getStatus() : null;
    }

    @Override
    public LoanStatusEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        for (LoanStatusEnum status : LoanStatusEnum.values()) {
            if (status.getStatus().equalsIgnoreCase(dbData)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + dbData);
    }
}
