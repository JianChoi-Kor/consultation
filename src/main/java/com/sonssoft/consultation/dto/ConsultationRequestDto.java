package com.sonssoft.consultation.dto;

import static com.sonssoft.consultation.entity.QConsultationInfo.consultationInfo;
import static com.sonssoft.consultation.entity.QFeedback.feedback;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.sonssoft.consultation.dto.interfaces.Paging;
import com.sonssoft.consultation.dto.interfaces.Predicatable;
import com.sonssoft.consultation.dto.interfaces.Sort;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ConsultationRequestDto {

    @Getter
    @Setter
    @Schema(description = "상담 등록 Request")
    public static class RegisterConsultation {

        @NotNull(message = "{consultant.employeeId.notnull}")
        @Schema(description = "상담사 고유값", example = "1")
        private Long employeeId;
        @NotNull(message = "{studentId.notnull}")
        @Schema(description = "학생 고유값", example = "1")
        private Long studentId;
        @Schema(description = "상담 내용", example = "이러이러한 내용의 상담을 진행했습니다.")
        private String content;
    }

    @Getter
    @Setter
    @Schema(description = "읽음 처리 Request")
    public static class ReadConsultation {

        @Hidden
        private Long consultationId;
        @NotNull(message = "{manager.employeeId.notnull}")
        @Schema(description = "담당자 고유값", example = "1")
        private Long employeeId;
    }

    @Getter
    @Setter
    @Schema(description = "상담 내역 조회 Request")
    public static class SearchConsultation implements Paging, Predicatable, Sort {

        @Schema(description = "상담사 고유값", example = "1")
        private Long consultantEmployeeId;
        @Schema(description = "읽음 처리 담당자 고유값", example = "1")
        private Long readManagerEmployeeId;
        @Schema(description = "피드백 담당자 고유값", example = "1")
        private Long feedbackManagerEmployeeId;
        @Schema(description = "읽음 여부에 대한 필터", example = "true")
        private Boolean readYn;
        @Schema(description = "피드백 여부에 대한 필터", example = "true")
        private Boolean feedbackYn;
        @Schema(description = "최신순 정렬 여부에 대한 필터", example = "true")
        private Boolean recentYn;
        private Long page;
        private Long limit;

        @Hidden
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

        @Hidden
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

        @Hidden
        @Override
        public long getOffset() {
            long pageToGo = page != null ? page : 1;
            long limitToGo = limit != null ? limit : 10;

            return (pageToGo - 1) * limitToGo;
        }

        @Hidden
        @Override
        public long getLimit() {
            return limit != null ? limit : 10;
        }
    }
}
