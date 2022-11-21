package com.bsuir.diploma.service.impl;

import com.bsuir.diploma.exception.model.*;
import com.bsuir.diploma.model.employee.User;
import com.bsuir.diploma.repository.UserRepository;
import com.bsuir.diploma.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bsuir.diploma.constant.UserImplConstant.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user) throws UserNotFoundException, EmailIsInvalidException, EmailExistException, UsernameIsInvalidException, UsernameExistException {
        validateNewUsernameAndEmail(EMPTY, user.getUsername(), user.getEmail());
        user.setIsActive(true);
        user.setIsNotLocked(true);
        user.setConfirmation(false);
        return userRepository.save(user);
    }

//    String password = generatePassword();
//    String encodedPassword = encodePassword(password);
//        user.setPassword(encodedPassword);

    private void validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException, UsernameIsInvalidException, EmailIsInvalidException {
        if (newUsername != null) {
            Pattern patternForUsername = Pattern.compile("[A-Za-z][A-Za-z0-9_$]+");
            Matcher matcherForUsername = patternForUsername.matcher(newUsername);
            boolean validateNewUsername = matcherForUsername.find();
            if (!validateNewUsername) {
                throw new UsernameIsInvalidException(NOT_A_VALID_USERNAME + newUsername);
            }
        }
        if (newEmail != null){
            Pattern patternForEmail = Pattern.compile("\\b[A-Za-z0-9_$.%+-]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,4}\\b");
            Matcher matcherForEmail = patternForEmail.matcher(newEmail);
            boolean validateNewEmail = matcherForEmail.find();
            if (!validateNewEmail){
                throw new EmailIsInvalidException(NOT_A_VALID_EMAIL + newEmail);
            }
        }
        User userByNewUsername = userRepository.findByUsername(newUsername);
        User userByNewEmail = userRepository.findByEmail(newEmail);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = userRepository.findByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
        }
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
