package com.sonssoft.consultation.enums.converter;

import com.sonssoft.consultation.enums.EmployeeType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmployeeTypeConverter implements AttributeConverter<EmployeeType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EmployeeType attribute) {
        return attribute.getLegacyCode();
    }

    @Override
    public EmployeeType convertToEntityAttribute(Integer dbData) {
        return EmployeeType.fromLegacyCode(dbData);
    }
}
