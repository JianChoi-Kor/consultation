package com.sonssoft.consultation.repository.querydsl.interfaces;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.sonssoft.consultation.dto.interfaces.Paging;
import com.sonssoft.consultation.entity.ConsultationInfo;

import java.util.List;
import java.util.Optional;

public interface ConsultationInfoRepositoryCustom {

    Optional<ConsultationInfo> findOne(Long id);
    QueryResults<ConsultationInfo> getConsultationList(Paging paging, List<Predicate> predicates, List<OrderSpecifier<?>> sorts);
}
