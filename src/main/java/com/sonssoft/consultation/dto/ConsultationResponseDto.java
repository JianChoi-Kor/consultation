package com.sonssoft.consultation.dto;

import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ConsultationResponseDto {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class RegisteredConsultation {

        private Long employeeId;
        private String employeeName;
        private Long studentId;
        private String studentName;
        private String content;

        public static RegisteredConsultation of(ConsultationInfo consultationInfo) {
            Employee employee = consultationInfo.getEmployee();
            Student student = consultationInfo.getStudent();

            return RegisteredConsultation.builder()
                    .employeeId(employee.getId())
                    .employeeName(employee.getName())
                    .studentId(student.getId())
                    .studentName(student.getName())
                    .content(consultationInfo.getContent())
                    .build();
        }
    }
}
