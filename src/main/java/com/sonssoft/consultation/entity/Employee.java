package com.sonssoft.consultation.entity;

import com.sonssoft.consultation.enums.EmployeeState;
import com.sonssoft.consultation.enums.EmployeeType;
import com.sonssoft.consultation.enums.converter.EmployeeStateConverter;
import com.sonssoft.consultation.enums.converter.EmployeeTypeConverter;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "employee")
@Entity
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = EmployeeTypeConverter.class)
    @Column(name = "type", nullable = false)
    private EmployeeType type;

    @Convert(converter = EmployeeStateConverter.class)
    @Column(name = "state", nullable = false)
    private EmployeeState state;
}
