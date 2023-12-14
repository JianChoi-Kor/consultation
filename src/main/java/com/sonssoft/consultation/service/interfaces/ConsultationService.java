package com.sonssoft.consultation.service.interfaces;

import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;

public interface ConsultationService {
    ConsultationDetail registerConsultation(RegisterConsultation param);
    ConsultationDetail getConsultation(Long consultationId);
}
