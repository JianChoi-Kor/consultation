package com.sonssoft.consultation.repository;

import com.sonssoft.consultation.entity.Employee;
import com.sonssoft.consultation.enums.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdAndType(Long id, EmployeeType employeeType);
}
