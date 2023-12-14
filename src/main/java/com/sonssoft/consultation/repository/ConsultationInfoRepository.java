package com.sonssoft.consultation.repository;

import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.repository.querydsl.interfaces.ConsultationInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationInfoRepository extends JpaRepository<ConsultationInfo, Long>, ConsultationInfoRepositoryCustom {
}
