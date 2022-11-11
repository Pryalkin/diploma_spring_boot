package com.bsuir.diploma.model;

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
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 5000)
    private String message;
    @OneToOne
    private Employee creator;
    @OneToMany
    private List<Emotion> emotions;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Viewing> views;
    @Column(name = "date_create")
    private Date dateCreate;
}
