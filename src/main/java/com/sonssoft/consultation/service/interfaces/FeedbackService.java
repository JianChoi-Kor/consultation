package com.sonssoft.consultation.service.interfaces;

import com.sonssoft.consultation.dto.ConsultationResponseDto.ConsultationDetail;
import com.sonssoft.consultation.dto.FeedbackRequestDto.RegisterOrModifyFeedback;

public interface FeedbackService {

    ConsultationDetail registerFeedback(RegisterOrModifyFeedback param);
}
