package com.bsuir.diploma.service;

import com.bsuir.diploma.model.employee.Employee;

public interface EmployeeService {
    Employee appoint(Employee employee);

    Employee findUserByUsername(String username);
}
