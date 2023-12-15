package com.sonssoft.consultation.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class FeedbackRequestDto {

    @Getter
    @Setter
    @Schema(description = "피드백 등록 Request")
    public static class RegisterFeedback {

        @Hidden
        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        @Schema(description = "피드백 담당자 고유값", example = "1")
        private Long employeeId;
        @Schema(description = "피드백 내용", example = "이러한 부분이 좋았습니다.")
        private String content;
    }

    @Getter
    @Setter
    public static class ModifyFeedback {

        @Hidden
        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        @Schema(description = "피드백 담당자 고유값", example = "1")
        private Long employeeId;
        @Schema(description = "피드백 내용", example = "이러한 부분이 좋았습니다.")
        private String content;
    }
}
