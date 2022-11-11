package com.bsuir.diploma.model;

import com.bsuir.diploma.model.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Employee creator;
    @Size(min = 1, max = 5000)
    private String message;
    @OneToMany
    private List<Emotion> emotions;
    @Column(name = "date_create")
    private Date dateCreate;
}
