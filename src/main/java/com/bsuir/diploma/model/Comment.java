package com.bsuir.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

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
    private Emotion emotions;
    @Column(name = "date_create")
    private Date dateCreate;
}
