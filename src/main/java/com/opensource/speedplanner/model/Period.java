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

@Getter
@Setter
@Entity
@Table(name="periods")
public class Period { //Agregar anotaciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Column(unique = true, name = "codes")
    private int code;
    @NotNull
    @Column(name = "start_dates")
    private Date startDate;
    @NotNull
    @Column(name = "end_dates")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_program_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    LearningProgram learningProgram;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inscription_process_id", referencedColumnName = "id")
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;
}