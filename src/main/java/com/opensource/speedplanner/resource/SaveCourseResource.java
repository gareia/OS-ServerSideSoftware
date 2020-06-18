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
    public String code;

    @NotBlank
    @NotNull
    public String name;

    @NotBlank
    @NotNull
    public Long totalNumberOfStudents;

    @NotNull
    public boolean isOptional;

    @NotNull
    public boolean isVirtual;

    @NotBlank
    @NotNull
    public int semester;

    @NotBlank
    @NotNull
    public int numberOfCredits;

    @NotBlank
    @NotNull
    public int credits;
}
