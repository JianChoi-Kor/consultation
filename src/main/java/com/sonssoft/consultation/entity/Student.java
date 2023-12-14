package com.sonssoft.consultation.entity;

import com.sonssoft.consultation.enums.StudentState;
import com.sonssoft.consultation.enums.converter.StudentStateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "student")
@Entity
public class Student {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = StudentStateConverter.class)
    @Column(name = "state", nullable = false)
    private StudentState state;
}
