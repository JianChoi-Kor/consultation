package com.sonssoft.consultation.dto;

import com.sonssoft.consultation.dto.FeedbackResponseDto.FeedbackDetail;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Feedback;
import com.sonssoft.consultation.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ConsultationResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ConsultationDetail {

        private Long employeeId;
        private String employeeName;
        private Long studentId;
        private String studentName;
        private String content;
        private FeedbackDetail feedbackDetail;

        public static ConsultationDetail of(ConsultationInfo consultationInfo) {
            Employee employee = consultationInfo.getEmployee();
            Student student = consultationInfo.getStudent();

            Feedback feedback = consultationInfo.getFeedback();
            if (feedback != null) {
                return ConsultationDetail.builder()
                        .employeeId(employee.getId())
                        .employeeName(employee.getName())
                        .studentId(student.getId())
                        .studentName(student.getName())
                        .content(consultationInfo.getContent())
                        .feedbackDetail(FeedbackDetail.of(feedback))
                        .build();
            } else {
                return ConsultationDetail.builder()
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
