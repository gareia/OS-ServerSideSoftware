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
@Table(name = "education_providers")
@Getter
@Setter
public class EducationProvider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotNull
	@NotBlank
	@Size(max = 40)
	@Column(unique = true)
    private String name;

	@NotNull
	@NotBlank
	@Column(name = "number_of_careers")
	private int numberOfCareers;

	@NotNull
	@NotBlank
	@Column(name = "current_period_code")
	private String currentPeriodCode;



	//RELATIONSHIPS

	//1 universidad tiene muchas carreras
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "educationProvider")
	private List<LearningProgram> learningPrograms;

	//1 perfil tiene 1 univ
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "educationProvider")
	private List<Profile> profiles;

	//1 univ tiene muchos cursos
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "educationProvider")
	private List<Course> course;

	//1 univ tiene muchos periodos
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "educationProvider")
	private List<Course> periods;

	//Relationships
	//learning programs, profile, course, period, subscription

	/*
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "academic_period_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Period academicPeriod;
	*/

	/*
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subscription_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Subscription subscription;

	@OneToOne(mappedBy = "education_providers")
	private Profile profile;

	 */
}
