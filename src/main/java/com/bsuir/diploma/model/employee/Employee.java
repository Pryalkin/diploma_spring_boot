package com.bsuir.diploma.model.employee;

import com.bsuir.diploma.model.employee.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @Column(name = "job_title")
    private String jobTitle;
    private String role;
    private String[] authorities;
    private Double premium;
}
