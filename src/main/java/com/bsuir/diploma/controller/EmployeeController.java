package com.bsuir.diploma.controller;

import com.bsuir.diploma.exception.ExceptionHandling;
import com.bsuir.diploma.model.employee.Employee;
import com.bsuir.diploma.model.employee.EmployeePrincipal;
import com.bsuir.diploma.service.EmployeeService;
import com.bsuir.diploma.utility.JWTTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bsuir.diploma.constant.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController extends ExceptionHandling {

    private final AuthenticationManager authenticationManager;
    private final EmployeeService employeeService;
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Employee employee) {
        log.info("Пользователь прошел авторизацию!!!");
        log.info(employee.toString());
        authenticate(employee.getUser().getUsername(), employee.getUser().getPassword());
        log.info("Все прошло успешно!!!");
        Employee loginEmployee = employeeService.findUserByUsername(employee.getUser().getUsername());
        EmployeePrincipal employeePrincipal = new EmployeePrincipal(loginEmployee);
        HttpHeaders jwtHeader = getJwtHeader(employeePrincipal);
        return new ResponseEntity<>(loginEmployee, OK);
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private HttpHeaders getJwtHeader(EmployeePrincipal employeePrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(employeePrincipal));
        return headers;
    }

}
