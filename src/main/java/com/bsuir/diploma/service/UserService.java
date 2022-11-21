package com.bsuir.diploma.service;

import com.bsuir.diploma.exception.model.*;
import com.bsuir.diploma.model.employee.User;
import org.springframework.messaging.MessagingException;

public interface UserService {

    User register(User user) throws UserNotFoundException, EmailIsInvalidException, EmailExistException, UsernameIsInvalidException, UsernameExistException;

}
