package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

public class SaveSectionScheduleResource {

    @NotNull
    @NotBlank
    public String sectionName;

    @NotNull
    @NotBlank
    public Date startTime;

    @NotNull
    @NotBlank
    public Date endTime;

    @NotNull
    @NotBlank
    public  int numberOfHours;

    @NotNull
    @NotBlank
    public String day;
}
