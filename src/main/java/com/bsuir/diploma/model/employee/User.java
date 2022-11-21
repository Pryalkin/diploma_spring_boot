package com.bsuir.diploma.model.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Minsk")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String phone;
    private String email;
    private String username;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Minsk")
    @Column(name = "last_login_date_display")
    private Date lastLoginDateDisplay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Europe/Minsk")
    @Column(name = "last_login_date")
    private Date lastLoginDate;
    private Boolean confirmation;
    @Column(name = "is_not_locked")
    private Boolean isNotLocked;
    @Column(name = "is_active")
    private Boolean isActive;
}
