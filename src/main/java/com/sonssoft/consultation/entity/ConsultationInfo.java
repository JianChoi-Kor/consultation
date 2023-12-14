package com.sonssoft.consultation.entity;

import com.sonssoft.consultation.entity.base.CreatedAndUpdatedAt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "consultation_info")
@Entity
public class ConsultationInfo extends CreatedAndUpdatedAt {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "content", nullable = true)
    private String content;

    @OneToOne(mappedBy = "consultationInfo")
    private Feedback feedback;
}
