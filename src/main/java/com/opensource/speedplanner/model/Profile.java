package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name ="profiles")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String names;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(name = "last_names")
    private String lastNames;

    @NotNull
    @NotBlank
    private boolean gender;


    private int semester;//not required

    //TODO: Long not long enough
    @NotNull
    @NotBlank
    @Column(unique = true)
    private Long idNumber; //dni

    //RELATIONSHIPS

    //1 user tiene solo 1 profile
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile")
    private User user;

    //TODO: Not necessary
    //1 profile tiene solo 1 universidad
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_provider_id", nullable = false)
    private EducationProvider educationProvider;

    //1 profile tiene solo 1 carrera
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_program_id", nullable = false)
    private LearningProgram learningProgram;

    //relationships
    //user, educationProvider, learningProgram

}
