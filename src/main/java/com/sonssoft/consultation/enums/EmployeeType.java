package com.sonssoft.consultation.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmployeeType {

    CONSULTANT("상담원", 0),
    MANAGER("담당자", 1);

    private final String desc;
    private final Integer legacyCode;

    EmployeeType(String desc, Integer legacyCode) {
        this.desc = desc;
        this.legacyCode = legacyCode;
    }

    public static EmployeeType fromLegacyCode(Integer legacyCode) {
        return Arrays.stream(EmployeeType.values())
                .filter(e -> e.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("not found 'EmployeeType' from legacyCode"));
    }
}
