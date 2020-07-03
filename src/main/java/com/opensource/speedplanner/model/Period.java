package com.opensource.speedplanner.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="periods")
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String code;

    @NotBlank
    @NotNull
    @Column(name = "start_date")
    private String startDate;

    @NotBlank
    @NotNull
    @Column(name = "end_date")
    private String endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "education_provider_id", nullable = false)
    private EducationProvider educationProvider;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "period")
    private List<InscriptionProcess> inscriptionProcesses;

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_program_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private EducationProvider educationProvider;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inscription_process_id", referencedColumnName = "id")
    @JsonIgnore
    private InscriptionProcess inscriptionProcess;*/

}
