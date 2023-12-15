package com.sonssoft.consultation.dto;

import com.sonssoft.consultation.dto.FeedbackResponseDto.FeedbackDetail;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Feedback;
import com.sonssoft.consultation.entity.Student;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ConsultationResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    @Schema(description = "상담 정보 Response")
    public static class ConsultationDetail {

        @Schema(description = "상담 고유값", example = "1")
        private Long consultationInfoId;
        @Schema(description = "상담원 고유값", example = "1")
        private Long employeeId;
        @Schema(description = "상담원 이름", example = "김상담")
        private String employeeName;
        @Schema(description = "학생 고유값", example = "1")
        private Long studentId;
        @Schema(description = "학생 이름", example = "이학생")
        private String studentName;
        @Schema(description = "상담 내용", example = "이러이러한 내용의 상담을 진행했습니다.")
        private String content;
        @Schema(description = "피드백 정보 Response")
        private FeedbackDetail feedbackDetail;

        public static ConsultationDetail of(ConsultationInfo consultationInfo) {
            Employee employee = consultationInfo.getEmployee();
            Student student = consultationInfo.getStudent();

            Feedback feedback = consultationInfo.getFeedback();
            if (feedback != null) {
                return ConsultationDetail.builder()
                        .consultationInfoId(consultationInfo.getId())
                        .employeeId(employee.getId())
                        .employeeName(employee.getName())
                        .studentId(student.getId())
                        .studentName(student.getName())
                        .content(consultationInfo.getContent())
                        .feedbackDetail(FeedbackDetail.of(feedback))
                        .build();
            } else {
                return ConsultationDetail.builder()
                        .consultationInfoId(consultationInfo.getId())
                        .employeeId(employee.getId())
                        .employeeName(employee.getName())
                        .studentId(student.getId())
                        .studentName(student.getName())
                        .content(consultationInfo.getContent())
                        .build();
            }
        }
    }
}
