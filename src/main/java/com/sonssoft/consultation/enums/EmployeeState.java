package com.sonssoft.consultation.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmployeeState {

    NORMAL("재직", 0),
    RETIREMENT("퇴직", 1);

    private final String desc;
    private final Integer legacyCode;

    EmployeeState(String desc, Integer legacyCode) {
        this.desc = desc;
        this.legacyCode = legacyCode;
    }

    public static EmployeeState fromLegacyCode(Integer legacyCode) {
        return Arrays.stream(EmployeeState.values())
                .filter(e -> e.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("not found 'EmployeeState' from legacyCode"));
    }
}
