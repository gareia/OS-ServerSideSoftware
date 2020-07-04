package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

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
    private String name; //IngSoftware Mecanico

    @NotNull
    @NotBlank
    private Long numberOfCourses;

}
