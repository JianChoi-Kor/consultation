package com.sonssoft.consultation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class FeedbackRequestDto {

    @Getter
    @Setter
    public static class RegisterFeedback {

        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        private Long employeeId;
        private String content;
    }

    @Getter
    @Setter
    public static class ModifyFeedback {

        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        private Long employeeId;
        private String content;
    }
}
