package com.sonssoft.consultation.repository;

import com.sonssoft.consultation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
