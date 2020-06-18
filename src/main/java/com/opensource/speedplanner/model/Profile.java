package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 20)
    @Column(name = "names")
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 20)
    @Column(name = "last_names")
    private String lastNames;

    @NotBlank
    @NotNull
    @Column(name = "genders")
    private boolean gender;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_provider_id", referencedColumnName = "id")
    @JsonIgnore
    private EducationProvider educationProvider;*/

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "learning_Program_id", referencedColumnName = "id")
    @JsonIgnore
    private LearningProgram learningProgram;*/

    @NotBlank
    @NotNull
    @Column(name = "semesters")
    private int semester;

    @NotBlank
    @NotNull
    @Column(name = "id_names" , unique = true)
    private Long idNumber;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private User user;
}