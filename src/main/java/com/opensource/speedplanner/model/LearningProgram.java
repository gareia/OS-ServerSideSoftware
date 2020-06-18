package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "learning_programs")
@Getter
@Setter
public class LearningProgram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
    @NotBlank
    @Size(max = 40)
	private String type; //CarreraUniv CarreraTecn

    @NotNull
    @NotBlank
    @Column(unique = true)
	private String name; //IngSoftware Mecanico

	@NotNull
	@NotBlank
    private Long numberOfCourses;

    //private List<Period> periodList;

    /*
	@ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinTable(name = "learning_programs_courses",
            joinColumns = {@JoinColumn(name = "learning_program_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
            @JsonIgnore
    private List<Course> curriculum;*/


	@OneToOne(mappedBy = "learningProgram")
    @JsonIgnore
    private Statistic statistic;

    /*@OneToOne(mappedBy = "learning_programs")
    private Profile profile;
    */

    //Porque EducationProvider tiene List<LearningProgram>
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EducationProvider educationProvider;
}
