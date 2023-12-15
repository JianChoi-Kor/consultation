package com.sonssoft.consultation.dto;

import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Feedback;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class FeedbackResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(description = "피드백 정보 Response")
    public static class FeedbackDetail {

        @Schema(description = "읽음 처리 담당자 고유값", example = "1")
        private Long readEmployeeId;
        @Schema(description = "읽음 처리 담당자 이름", example = "최담당")
        private String readEmployeeName;
        @Schema(description = "피드백 담당자 고유값", example = "1")
        private Long feedbackEmployeeId;
        @Schema(description = "피드백 담당자 이름", example = "최담당")
        private String feedbackEmployeeName;
        @Schema(description = "피드백 내용", example = "이런 부분에서의 상담이 좋았습니다.")
        private String content;

        public static FeedbackDetail of(Feedback feedback) {
            Employee readEmployee = feedback.getReadEmployee();
            Employee feedbackEmployee = feedback.getFeedbackEmployee();

            // 읽기만 하고 피드백이 등록되지 않은 경우에 대한 분기 처리
            if (feedbackEmployee == null) {
                return FeedbackDetail.builder()
                        .readEmployeeId(readEmployee.getId())
                        .readEmployeeName(readEmployee.getName())
                        .build();
            } else {
                return FeedbackDetail.builder()
                        .readEmployeeId(readEmployee.getId())
                        .readEmployeeName(readEmployee.getName())
                        .feedbackEmployeeId(feedbackEmployee.getId())
                        .feedbackEmployeeName(feedbackEmployee.getName())
                        .content(feedback.getContent())
                        .build();
            }
        }
    }
}
