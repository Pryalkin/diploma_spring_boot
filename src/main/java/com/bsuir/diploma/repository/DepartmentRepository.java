package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
