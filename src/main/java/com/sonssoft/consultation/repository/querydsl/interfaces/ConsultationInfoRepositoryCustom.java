package com.sonssoft.consultation.repository.querydsl.interfaces;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.sonssoft.consultation.entity.ConsultationInfo;

import java.util.List;
import java.util.Optional;

public interface ConsultationInfoRepositoryCustom {

    Optional<ConsultationInfo> findOne(Long id);
    List<ConsultationInfo> getConsultationList(List<Predicate> predicates, List<OrderSpecifier<?>> sorts);
}
