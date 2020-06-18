package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="statistics")
@Getter
@Setter
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name ="women_percentage_in_periods")
    private Double womenPercentageInPeriod;

    @NotNull
    @NotBlank
    @Column(name ="men_percentage_in_periods")
    private Double menPercentageInPeriod;

    @NotNull
    @NotBlank
    @Column(name ="registered_students_in_periods")
    private int RegisteredStudentsInPeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "learning_program_id", referencedColumnName = "id")
    @JsonIgnore
    private LearningProgram learningProgram;

    @OneToOne(mappedBy = "statistic")
    @JsonIgnore
    private User user;
}
