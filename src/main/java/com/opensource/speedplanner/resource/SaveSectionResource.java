package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveSectionResource {
    @NotNull
    @NotBlank
    @Column(unique = true)
    @Size(max = 5)
    private String sectionName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String venue; //sede

    @NotNull
    @NotBlank
    private int vacancy;

    @NotNull
    @NotBlank
    private int numberOfHours;

    @NotNull
    @NotBlank
    private String professorName; //for now
    //o relacion con professor o prop professor

    /*
    @NotNull
    @NotBlank
    private Long course_id;*/

}
