package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "classrooms")
@Getter
@Setter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "classroom_names")
    private String classroomName;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "types")
    private String type;

    @NotNull
    @NotBlank
    @Column(name = "capacity")
    private int capacity;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "classrooms_courses",
            joinColumns = {@JoinColumn(name = "classroom_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    @JsonIgnore
    List<Course> courses;
}