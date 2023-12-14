package com.sonssoft.consultation.repository.querydsl;

import static com.sonssoft.consultation.entity.QConsultationInfo.consultationInfo;
import static com.sonssoft.consultation.entity.QEmployee.employee;
import static com.sonssoft.consultation.entity.QStudent.student;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.repository.querydsl.interfaces.ConsultationInfoRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ConsultationInfoRepositoryImpl implements ConsultationInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ConsultationInfo> findOne(Long id) {
        ConsultationInfo result = queryFactory.selectFrom(consultationInfo)
                .join(consultationInfo.employee, employee).fetchJoin()
                .join(consultationInfo.student, student).fetchJoin()
                .where(consultationInfo.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
