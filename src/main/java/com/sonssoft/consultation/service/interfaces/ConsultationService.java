package com.sonssoft.consultation.service.interfaces;

import com.querydsl.core.QueryResults;
import com.sonssoft.consultation.dto.ConsultationRequestDto;
import com.sonssoft.consultation.dto.ConsultationRequestDto.ReadConsultation;
import com.sonssoft.consultation.dto.ConsultationRequestDto.RegisterConsultation;
import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;

import java.util.List;

public interface ConsultationService {

    ConsultationDetail registerConsultation(RegisterConsultation param);
    ConsultationDetail getConsultation(Long consultationId);
    ConsultationDetail readConsultation(ReadConsultation param);
    QueryResults<ConsultationDetail> getConsultationList(ConsultationRequestDto.SearchConsultation param);
}
