package com.sonssoft.consultation.dto;

import static com.sonssoft.consultation.entity.QConsultationInfo.consultationInfo;
import static com.sonssoft.consultation.entity.QFeedback.feedback;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.sonssoft.consultation.dto.interfaces.Predicatable;
import com.sonssoft.consultation.dto.interfaces.Sort;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ConsultationRequestDto {

    @Getter
    @Setter
    public static class RegisterConsultation {

        @NotNull(message = "{consultant.employeeId.notnull}")
        private Long employeeId;
        @NotNull(message = "{studentId.notnull}")
        private Long studentId;
        private String content;
    }

    @Getter
    @Setter
    public static class ReadConsultation {

        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        private Long employeeId;
    }

    @Getter
    @Setter
    public static class SearchConsultation implements Predicatable, Sort {

        private Long consultantEmployeeId;
        private Long readManagerEmployeeId;
        private Long feedbackManagerEmployeeId;
        private Boolean readYn;
        private Boolean feedbackYn;
        private Boolean recentYn;

        public List<Predicate> getPredicates() {
            List<Predicate> where = new ArrayList<>();
            // 상담사 조회 조건이 있을 경우
            if (consultantEmployeeId != null) {
                where.add(consultationInfo.employee.id.eq(consultantEmployeeId));
            }
            // 읽음 처리를 한 담당자 조건이 있을 경우
            if (readManagerEmployeeId != null) {
                where.add(feedback.readEmployee.id.eq(readManagerEmployeeId));
            }
            // 피드백 담당자 조건이 있을 경우
            if (feedbackManagerEmployeeId != null) {
                where.add(feedback.feedbackEmployee.id.eq(feedbackManagerEmployeeId));
            }
            // 읽음 처리 되었는지 여부
            if (readYn != null) {
                if (readYn) {
                    where.add(feedback.isNotNull());
                } else {
                    where.add(feedback.isNull());
                }
            }
            // 피드백 내용 유무
            if (feedbackYn != null) {
                if (feedbackYn) {
                    where.add(feedback.content.isNotNull());
                } else {
                    where.add(feedback.content.isNull());
                }
            }

            return where;
        }

        public List<OrderSpecifier<?>> getSorts() {
            List<OrderSpecifier<?>> orderBy = new ArrayList<>();
            // 최신순 정렬 여부
            if (recentYn != null) {
                if (recentYn) {
                    orderBy.add(consultationInfo.createdAt.desc());
                } else {
                    orderBy.add(consultationInfo.createdAt.asc());
                }
            }

            return orderBy;
        }
    }
}
