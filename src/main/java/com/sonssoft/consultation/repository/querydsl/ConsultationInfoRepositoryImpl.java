package com.sonssoft.consultation.repository.querydsl;

import static com.sonssoft.consultation.entity.QConsultationInfo.consultationInfo;
import static com.sonssoft.consultation.entity.QEmployee.employee;
import static com.sonssoft.consultation.entity.QStudent.student;
import static com.sonssoft.consultation.entity.QFeedback.feedback;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sonssoft.consultation.entity.ConsultationInfo;
import com.sonssoft.consultation.entity.QEmployee;
import com.sonssoft.consultation.repository.querydsl.interfaces.ConsultationInfoRepositoryCustom;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
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

    @Override
    public List<ConsultationInfo> getConsultationList(List<Predicate> predicates, List<OrderSpecifier<?>> sorts) {
        QEmployee consultant = new QEmployee("consultant");
        QEmployee readManager = new QEmployee("readManager");
        QEmployee feedbackManager = new QEmployee("feedbackManager");

        BooleanBuilder builder = new BooleanBuilder();
        predicates.stream().filter(Objects::nonNull).forEach(builder::and);

        return queryFactory.selectFrom(consultationInfo)
                .leftJoin(consultationInfo.employee, consultant).fetchJoin()
                .leftJoin(consultationInfo.student, student).fetchJoin()
                .leftJoin(consultationInfo.feedback, feedback).fetchJoin()
                .leftJoin(feedback.readEmployee, readManager).fetchJoin()
                .leftJoin(feedback.feedbackEmployee, feedbackManager).fetchJoin()
                .where(builder)
                .orderBy(sorts.toArray(new OrderSpecifier[0]))
                .fetch();
    }
}
