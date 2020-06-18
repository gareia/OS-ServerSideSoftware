package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="possible_schedules")
@Getter
@Setter
public class PossibleSchedule {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Column(name="credits")
    private int credits;
    @Column(name = "is_final")
    @NotNull
    private boolean isFinal;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "possible_schedules_courses",
            joinColumns = {@JoinColumn(name = "possible_schedule_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    @JsonIgnore
    private List<Course> courses;

    //Porque InscriptionProcess tiene List<InscriptionProcess>
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inscription_process_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;
}
