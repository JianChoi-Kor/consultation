package com.sonssoft.consultation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ConsultationRequestDto {

    @Getter
    @Setter
    public static class RegisterConsultation {

        @NotNull(message = "{employeeId.notnull}")
        private Long employeeId;
        @NotNull(message = "{studentId.notnull}")
        private Long studentId;
        private String content;
    }
}
