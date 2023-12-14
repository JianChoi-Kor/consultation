package com.sonssoft.consultation.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StudentState {

    NORMAL("재원", 0),
    WITHDRAWAL("퇴원", 1);

    private final String desc;
    private final Integer legacyCode;

    StudentState(String desc, Integer legacyCode) {
        this.desc = desc;
        this.legacyCode = legacyCode;
    }

    public static StudentState fromLegacyCode(Integer legacyCode) {
        return Arrays.stream(StudentState.values())
                .filter(e -> e.getLegacyCode().equals(legacyCode))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("not found 'StudentState' from legacyCode"));
    }
}
