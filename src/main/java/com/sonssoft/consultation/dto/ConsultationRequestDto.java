package com.sonssoft.consultation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class ConsultationRequestDto {

    @Getter
    @Setter
    public static class RegisterConsultation {

        @NotNull(message = "{consultant.employeeId.notnull}")
        private Long employeeId;
        @NotNull(message = "{studentId.notnull}")
        private Long studentId;
        private String content;
    }

    @Getter
    @Setter
    public static class ReadConsultation {

        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        private Long employeeId;
    }
}
