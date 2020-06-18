package com.opensource.speedplanner.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 5)
    @Column(unique=true)
    private String code;

    @NotBlank
    @NotNull
    @Size(max = 25)
    @Column(name= "names")
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 40)
    @Column(name= "total_number_of_students")
    private Long totalNumberOfStudents;

    @NotNull
    @Column(name= "is_optional")
    private boolean isOptional;

    @NotNull
    @Column(name= "is_virtual")
    private boolean isVirtual;

    @NotBlank
    @NotNull
    @Column(name= "semester")
    private int semester;

    @NotBlank
    @NotNull
    @Column(name= "number_of_credits")
    private int numberOfCredits;

    @NotBlank
    @NotNull
    @Column(name= "credits")
    private int credits;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "courses")
    @JsonIgnore
    private List<Classroom> classrooms;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course requisite;

    @OneToMany(mappedBy = "requisite")
    private List<Course> courses;
*/
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "courses")
    @JsonIgnore
    private List<PossibleSchedule> possibleSchedules;
/*
	@OneToOne(mappedBy = "courses")
    private SectionRequest sectionRequest;


 */
     //Porque InscriptionProcess tiene List<Course>
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inscription_process_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;

    //Porque LearningProgram tiene List<Course>

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "curriculum")
    @JsonIgnore
    private List<LearningProgram> learningPrograms;


}

