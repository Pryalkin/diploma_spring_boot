package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
