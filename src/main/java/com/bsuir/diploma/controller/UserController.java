package com.bsuir.diploma.controller;

import com.bsuir.diploma.exception.ExceptionHandling;
import com.bsuir.diploma.exception.model.*;
import com.bsuir.diploma.model.employee.User;
import com.bsuir.diploma.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController  extends ExceptionHandling {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailIsInvalidException, EmailExistException, UsernameIsInvalidException, UsernameExistException {
        log.info("Пользователь получен!!!");
        User newUser = userService.register(user);
        return new ResponseEntity<>(newUser, OK);
    }

}
