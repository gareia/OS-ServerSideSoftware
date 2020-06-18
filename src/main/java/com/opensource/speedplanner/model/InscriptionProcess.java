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

    //private List<Course> courses;

    //private List<PossibleSchedule> possibleSchedules;

    //private List<Constraint> constraints;

    //private List<SectionRequest> sectionRequests;
/*
	@OneToOne(mappedBy = "inscription_processes")
	private Period period;*/

	@OneToOne(mappedBy = "inscriptionProcess")
	@JsonIgnore
	private User user;
}
