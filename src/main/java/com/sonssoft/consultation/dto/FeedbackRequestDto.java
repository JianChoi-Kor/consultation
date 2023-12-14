package com.sonssoft.consultation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class FeedbackRequestDto {

    @Getter
    @Setter
    public static class RegisterOrModifyFeedback {

        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        private Long employeeId;
        private String content;
    }
}
