package com.sonssoft.consultation.enums.converter;

import com.sonssoft.consultation.enums.EmployeeState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmployeeStateConverter implements AttributeConverter<EmployeeState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EmployeeState attribute) {
        return attribute.getLegacyCode();
    }

    @Override
    public EmployeeState convertToEntityAttribute(Integer dbData) {
        return EmployeeState.fromLegacyCode(dbData);
    }
}
