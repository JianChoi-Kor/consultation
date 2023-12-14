package com.sonssoft.consultation.repository;

import com.sonssoft.consultation.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
