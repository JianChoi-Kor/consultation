package com.sonssoft.consultation.repository;

import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    Feedback findByConsultationInfo(ConsultationInfo consultationInfo);
}
