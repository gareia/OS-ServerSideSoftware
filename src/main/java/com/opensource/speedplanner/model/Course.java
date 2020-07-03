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
    @Size(max = 10)
    @Column(unique=true)
    private String code;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    @Column(name= "is_optional")
    private boolean isOptional;

    @NotNull
    @Column(name= "is_virtual")
    private boolean isVirtual;

    @NotBlank
    @NotNull
    private int semester;

    @NotBlank
    @NotNull
    private int credits;

    //TODO: SUM Students in course
    @Size(max = 40)
    @Column(name= "total_nmbr_of_stdnts")
    private Long totalNumberOfStudents;

    //RELATIONSHIPS
    //course, education provider, courses, course and sections,
    //possible schedules
    //1 curso puede tener muchos cursos requisitos
    //1 requisito puede ser de muchos cursos

    //1 curso pertence a 1 carrera
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<LearningProgram> learningPrograms;

    //1 curso tiene muchas secciones
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Section> sections;

    //1 curso tiene solo 1 univ
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_provider_id", nullable = false)
    private EducationProvider educationProvider;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<InscriptionProcess> inscriptionProcesses;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "course")
    private Constraint constraint;


/*


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "possible_schedule_id", nullable = false)
    private PossibleSchedule possibleSchedule;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "courses")
    @JsonIgnore
    private List<Classroom> classrooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course requisite;

    @OneToMany(mappedBy = "requisite")
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "courses")
    @JsonIgnore
    private List<PossibleSchedule> possibleSchedules;

	@OneToOne(mappedBy = "courses")
    private SectionRequest sectionRequest;

    //Porque LearningProgram tiene List<Course>
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "courses")
    @JsonIgnore
    private List<LearningProgram> learningPrograms;

    //Porque InscriptionProcess tiene List<Course>
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inscription_process_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;*/
}

