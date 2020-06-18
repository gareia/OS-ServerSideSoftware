package com.opensource.speedplanner.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "section_schedules")
@Getter
@Setter
public class SectionSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name ="section_names", unique = true)
    private String sectionName;

    @NotNull
    @NotBlank
    @Column(name ="start_times")
    private Date startTime;

    @NotNull
    @NotBlank
    @Column(name ="end_times")
    private Date endTime;

    @NotNull
    @NotBlank
    @Column(name ="number_of_hours")
    private  int numberOfHours;

    @NotNull
    @NotBlank
    @Column(name ="days")
    private String day;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Section section;
}