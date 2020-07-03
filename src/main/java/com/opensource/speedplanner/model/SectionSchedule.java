package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name ="section_schedules")
@Getter
@Setter
public class SectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "start_time")
    @Size(max = 4) //HHMM
    private String startTime;

    @NotNull
    @NotBlank
    @Column(name = "end_time")
    @Size(max = 4) //HHMM
    private String endTime;

    @NotNull
    @NotBlank
    @Column(name = "number_of_hours")
    private int numberOfHours;

    @NotNull
    @NotBlank
    private String day; //enum day

    @NotNull
    @NotBlank
    @Column(name = "classroom_name")
    private String classroomName;
    //relationship with section and classroom o propiedad

    //RELATIONSHIPS

    //1 section schedule tiene solo 1 section
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
}
