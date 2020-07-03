package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveCourseResource {

    @NotBlank
    @NotNull
    @Size(max = 10)
    @Column(unique=true)
    private String code;

    @NotBlank
    @NotNull
    @Size(max = 30)
    private String name;

    @NotNull
    private boolean isOptional;

    @NotNull
    private boolean isVirtual;

    @NotBlank
    @NotNull
    private int semester;

    @NotBlank
    @NotNull
    private int credits;

    /*
    @NotBlank
    @NotNull
    private Long education_provider_id;*/
}
