package com.sonssoft.consultation.enums.converter;

import com.sonssoft.consultation.enums.StudentState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StudentStateConverter implements AttributeConverter<StudentState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StudentState attribute) {
        return attribute.getLegacyCode();
    }

    @Override
    public StudentState convertToEntityAttribute(Integer dbData) {
        return StudentState.fromLegacyCode(dbData);
    }
}
