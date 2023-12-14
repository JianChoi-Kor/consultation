package com.sonssoft.consultation.service.interfaces;

import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.ConsultationResponseDto.RegisteredConsultation;

public interface ConsultationService {
    RegisteredConsultation registerConsultation(RegisterConsultation param);
}
