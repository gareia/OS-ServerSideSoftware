package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveCourseResource {

    @NotBlank
    @NotNull
    private String code;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private Long totalNumberOfStudents;

    @NotNull
    private boolean isOptional;

    @NotNull
    private boolean isVirtual;

    @NotBlank
    @NotNull
    private int semester;

    @NotBlank
    @NotNull
    private int numberOfCredits;

    @NotBlank
    @NotNull
    private int credits;
}
