package com.opensource.speedplanner.resource;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSectionScheduleResource {

    @NotNull
    @NotBlank
    @Size(max = 4) //HHMM
    private String startTime;

    @NotNull
    @NotBlank
    @Size(max = 4) //HHMM
    private String endTime;

    @NotNull
    @NotBlank
    private int numberOfHours;

    @NotNull
    @NotBlank
    private String day; //enum day

    @NotNull
    @NotBlank
    private String classroomName;
    //relationship with section and classroom o propiedad
/*
    @NotNull
    @NotBlank
    private Long section_id;*/
}
