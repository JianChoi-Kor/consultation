package com.sonssoft.consultation.entity;

import com.sonssoft.consultation.entity.base.CreatedAndUpdatedAt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "feedback")
@Entity
public class Feedback extends CreatedAndUpdatedAt {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_info_id", referencedColumnName = "id")
    private ConsultationInfo consultationInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "read_employee_id", referencedColumnName = "id")
    private Employee readEmployee;

    @Column(name = "content", nullable = true)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_employee_id", referencedColumnName = "id")
    private Employee feedbackEmployee;

    @Column(name = "feedback_at", nullable = true)
    private LocalDateTime feedbackAt;
}
