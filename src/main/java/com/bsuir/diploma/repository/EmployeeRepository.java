package com.bsuir.diploma.repository;

import com.bsuir.diploma.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUserUsername(String username);
}
