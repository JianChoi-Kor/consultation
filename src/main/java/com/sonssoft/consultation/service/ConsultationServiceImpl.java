package com.sonssoft.consultation.service;

import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Student;
import com.sonssoft.consultation.enums.EmployeeType;
import com.sonssoft.consultation.exception.DataNotFoundException;
import com.sonssoft.consultation.repository.ConsultationInfoRepository;
import com.sonssoft.consultation.repository.EmployeeRepository;
import com.sonssoft.consultation.repository.FeedbackRepository;
import com.sonssoft.consultation.repository.StudentRepository;
import com.sonssoft.consultation.service.interfaces.ConsultationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationInfoRepository consultationInfoRepository;
    private final EmployeeRepository employeeRepository;
    private final StudentRepository studentRepository;
    private final FeedbackRepository feedbackRepository;

    @Transactional
    @Override
    public ConsultationDetail registerConsultation(RegisterConsultation param) {
        // 해당하는 상담사가 존재하는지 여부 확인
        Employee consultant = employeeRepository.findByIdAndType(param.getEmployeeId(), EmployeeType.CONSULTANT)
                .orElseThrow(() -> new DataNotFoundException("해당하는 상담사가 존재하지 않습니다."));

        // 해당하는 학생이 존재하는지 여부 확인
        Student student = studentRepository.findById(param.getStudentId())
                .orElseThrow(() -> new DataNotFoundException("해당하는 학생이 존재하지 않습니다."));

        ConsultationInfo consultationInfo = ConsultationInfo.builder()
                .student(student)
                .employee(consultant)
                .content(param.getContent())
                .build();

        // save
        consultationInfoRepository.save(consultationInfo);

        return ConsultationDetail.of(consultationInfo);
    }

    @Transactional
    @Override
    public ConsultationDetail getConsultation(Long consultationId) {
        // 해당하는 상담 내역이 존재하는지 여부 확인
        ConsultationInfo consultationInfo = consultationInfoRepository.findOne(consultationId)
                .orElseThrow(() -> new DataNotFoundException("해당하는 상담 정보가 존재하지 않습니다."));

        return ConsultationDetail.of(consultationInfo);
    }
}
