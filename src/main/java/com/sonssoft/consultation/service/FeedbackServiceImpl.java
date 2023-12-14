package com.sonssoft.consultation.service;

import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterOrModifyFeedback;
import com.sonssoft.consultation.dto.FeedbackResponseDto.FeedbackDetail;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.entity.Feedback;
import com.sonssoft.consultation.enums.EmployeeType;
import com.sonssoft.consultation.exception.DataNotFoundException;
import com.sonssoft.consultation.repository.ConsultationInfoRepository;
import com.sonssoft.consultation.repository.EmployeeRepository;
import com.sonssoft.consultation.repository.FeedbackRepository;
import com.sonssoft.consultation.service.interfaces.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final ConsultationInfoRepository consultationInfoRepository;
    private final EmployeeRepository employeeRepository;
    private final FeedbackRepository feedbackRepository;

    @Transactional
    @Override
    public ConsultationDetail registerFeedback(RegisterOrModifyFeedback param) {
        // 해당하는 상담 내역이 존재하는지 여부 확인
        ConsultationInfo consultationInfo = consultationInfoRepository.findOne(param.getConsultationId())
                .orElseThrow(() -> new DataNotFoundException("해당하는 상담 정보가 존재하지 않습니다."));

        // 해당하는 담당자가 존재하는지 여부 확인
        Employee manager = employeeRepository.findByIdAndType(param.getEmployeeId(), EmployeeType.MANAGER)
                .orElseThrow(() -> new DataNotFoundException("해당하는 담당자가 존재하지 않습니다."));

        // 읽음 처리 또는 피드백이 등록되어 있는지 확인
        Feedback feedback = feedbackRepository.findByConsultationInfo(consultationInfo);
        if (feedback != null) {
            // 피드백이 등록되어 있는 경우
            if (feedback.getFeedbackEmployee() != null) {
                throw new IllegalStateException("기존에 등록된 피드백이 존재합니다.");
            } else {
                feedback.registerFeedback(manager, param.getContent(), LocalDateTime.now());
            }
        } else {
            feedback = Feedback.builder()
                    .consultationInfo(consultationInfo)
                    .readEmployee(manager)
                    .content(param.getContent())
                    .feedbackEmployee(manager)
                    .feedbackAt(LocalDateTime.now())
                    .build();
        }

        feedbackRepository.save(feedback);

        return ConsultationDetail.of(consultationInfo, FeedbackDetail.of(feedback));
    }
}
