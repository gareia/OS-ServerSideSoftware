package com.opensource.speedplanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "inscription_processes")
@Getter
@Setter
public class InscriptionProcess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //RELATIONSHIPS

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "period_id", nullable = false)
	private Period period;


	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "i_process_courses",
            joinColumns = {@JoinColumn(name = "i_process_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;


    private int totalCredits; //TODO: Sum generated

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "i_process_sections",
            joinColumns = {@JoinColumn(name = "i_process_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private List<Section> sections;



    /*@OneToMany(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "possible_schedule_id", nullable = false)
    private List<Course> possibleSchedule;*/

    //private List<Constraint> constraints;

    //private List<SectionRequest> sectionRequests;
}
