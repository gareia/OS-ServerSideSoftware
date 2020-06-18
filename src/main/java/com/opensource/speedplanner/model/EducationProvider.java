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
	@Size(max = 30)
	@Column(unique = true)
    private String name;

	/*
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "academic_period_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Period academicPeriod;
	*/

	@NotNull
	@NotBlank
    private int numberOfCareers;

    //private List<LearningProgram> careers;

    //private List<Classroom> classrooms;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "subscription_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Subscription subscription;

	/*@OneToOne(mappedBy = "education_providers")
	private Profile profile;*/

}
