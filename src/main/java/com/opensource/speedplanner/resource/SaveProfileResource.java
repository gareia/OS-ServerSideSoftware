package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveProfileResource {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String names;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastNames;

    @NotNull
    @NotBlank
    private boolean gender;//true woman false man

    private int semester;//not required

    @NotNull
    @NotBlank
    @Column(unique = true)
    private Long idNumber; //dni


    @NotNull
    @NotBlank
    private Long education_provider_id;

    @NotNull
    @NotBlank
    private Long learning_program_id;
}
