package com.bsuir.diploma.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String phone;
    @Email
    private String email;
    private String username;
    private String password;
    @Column(name = "is_not_locked")
    private boolean isNotLocked;
    @Column(name = "is_active")
    private boolean isActive;
}