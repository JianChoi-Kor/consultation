package com.sonssoft.consultation.repository.querydsl.interfaces;

import com.sonssoft.consultation.entity.ConsultationInfo;

import java.util.Optional;

public interface ConsultationInfoRepositoryCustom {

    Optional<ConsultationInfo> findOne(Long id);
}
