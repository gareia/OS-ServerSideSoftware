package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveSectionResource {
    @NotNull
    @NotBlank
    @Size(max = 5)
    private String sectionName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String venue;

    @NotNull
    @NotBlank
    private int registeredStudents;

    @NotNull
    @NotBlank
    private int numberOfHours;
}
