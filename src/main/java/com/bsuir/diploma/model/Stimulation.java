package com.bsuir.diploma.model;

import com.bsuir.diploma.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stimulation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Employee creator;
    private String message;
    private Double sum;
    @Column(name = "date_create")
    private Date dateCreate;
}
