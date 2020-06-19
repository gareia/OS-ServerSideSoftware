package com.opensource.speedplanner.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opensource.speedplanner.model.EducationProvider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveLearningProgramResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    private String type; //CarreraUniv CarreraTecn

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name; //IngSoftware Mecanico

    @NotNull
    @NotBlank
    private Long numberOfCourses;
}
