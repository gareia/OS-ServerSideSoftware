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
@Table(name = "sections")
@Getter
@Setter
public class Section { //agregar anotaciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 4)
    @Column(name = "section_names")
    private String sectionName;

    @NotBlank
    @NotNull
    @Size(max = 20)
    @Column(name = "venues")
    private String venue;

    @NotBlank
    @NotNull
    @Column(name = "vacancies")
    private int vacancy;

    @NotBlank
    @NotNull
    @Column(name = "registeredStudentses")
    private int registeredStudents;

    @NotBlank
    @NotNull
    @Column(name = "numberOfHourses" )
    private int numberOfHours;

    //SectionSchedules list

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Course course;
}