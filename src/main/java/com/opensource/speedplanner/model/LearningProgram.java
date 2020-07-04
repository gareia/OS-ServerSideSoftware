package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

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
	private String name; //IngSoftware Mecanico

	@NotNull
	@NotBlank
    @Column(name = "number_of_courses")
    private Long numberOfCourses;

	//RELATIONSHIPS

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "learningProgram")
	private List<Profile> profiles;

	//1 carrera tiene muchos cursos
    //1 curso tiene muchas carreras
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "l_program_courses",
    joinColumns = {@JoinColumn(name = "l_program_id")},
    inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;

    //muchas carreras son de 1 univ
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="education_provider_id", nullable = false)
    private EducationProvider educationProvider;


	//Relationships
    //Education provider, course, profile, statistics

    //private List<Period> periodList;

    /*
	@ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            @JoinTable(name = "learning_programs_courses",
            joinColumns = {@JoinColumn(name = "learning_program_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
            @JsonIgnore
    private List<Course> curriculum;


	@OneToOne(mappedBy = "learning_programs")
    private Statistic statistic;

    @OneToOne(mappedBy = "learning_programs")
    private Profile profile;
    */

    //Porque EducationProvider tiene List<LearningProgram>
    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EducationProvider educationProvider;*/
}
