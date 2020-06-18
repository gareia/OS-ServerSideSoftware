package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveProfessorResource {
/*
    @NotBlank
    @NotNull
    private int code;

 */

    @NotBlank
    @NotNull
    private Long idNumber;

    @NotBlank
    @NotNull
    private String names;

    @NotBlank
    @NotNull
    private String lastNames;
}
