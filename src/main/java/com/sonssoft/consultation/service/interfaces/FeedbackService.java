package com.sonssoft.consultation.service.interfaces;

import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;
import com.sonssoft.consultation.dto.FeedbackRequestDto.ModifyFeedback;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterFeedback;

public interface FeedbackService {

    ConsultationDetail registerFeedback(RegisterFeedback param);
    ConsultationDetail modifyConsultation(ModifyFeedback param);
}
