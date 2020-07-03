package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name ="sections")
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "section_name", unique = true)
    @Size(max = 5)
    private String sectionName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String venue; //sede

    @NotNull
    @NotBlank
    private int vacancy;

    @NotNull
    @NotBlank
    @Column(name = "number_of_hours")
    private int numberOfHours;

    @Column(name = "registered_students")
    private int registeredStudents; //not required

    @NotNull
    @NotBlank
    @Column(name = "professor_name")
    private String professorName; //for now
    //o relacion con professor o prop professor


    //RELATIONSHIPS

    //1 section tiene varios section schedules
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "section")
    private List<SectionSchedule> sectionSchedules;

    //1 course tiene varias secciones
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sections")
    private List<InscriptionProcess> inscriptionProcesses;

    //relationships
    //sectionSchedule, course y professor


}
