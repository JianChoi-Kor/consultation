package com.sonssoft.consultation.dto;

import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Feedback;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class FeedbackResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class FeedbackDetail {

        private Long readEmployeeId;
        private String readEmployeeName;
        private Long feedbackEmployeeId;
        private String feedbackEmployeeName;
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
