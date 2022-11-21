package com.bsuir.diploma.service.impl;

import com.bsuir.diploma.model.employee.Employee;
import com.bsuir.diploma.model.employee.EmployeePrincipal;
import com.bsuir.diploma.model.employee.User;
import com.bsuir.diploma.repository.EmployeeRepository;
import com.bsuir.diploma.repository.UserRepository;
import com.bsuir.diploma.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

import static com.bsuir.diploma.constant.UserImplConstant.*;

@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;
//    private final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUserUsername(username);
        if (employee == null) {
            log.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempt(employee);
            User user = employee.getUser();
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            user = userRepository.save(user);
            employee.setUser(user);
            employee = employeeRepository.save(employee);
            EmployeePrincipal employeePrincipal = new EmployeePrincipal(employee);
            log.info(FOUND_USER_BY_USERNAME + username);
            log.info(employee.toString());
            return employeePrincipal;
        }
    }

    @Override
    public Employee appoint(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findUserByUsername(String username) {
        return employeeRepository.findByUserUsername(username);
    }


    private void validateLoginAttempt(Employee employee) {
        if(employee.getUser().getIsNotLocked()){
            if (loginAttemptService.hasExceededMaxAttempts(employee.getUser().getUsername())){
                employee.getUser().setIsNotLocked(false);
            } else {
                employee.getUser().setIsNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(employee.getUser().getUsername());
        }
    }

}
