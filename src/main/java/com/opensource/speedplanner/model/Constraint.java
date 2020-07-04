package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//not yet



@Entity
@Table(name = "constraints")
@Getter
@Setter
public class Constraint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private String startTime;


    @Column(name = "number_of_hours")
    private int numberOfHours;

    @Column(name = "professor_name")
    private String professorName;

    /*@Column(name = "end_time")
    private String endTime;*/


    //private String days;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;


}
