package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionScheduleResource {

    private Long id;
    private String startTime;
    private String endTime;
    private int numberOfHours;
    private String day; //enum day
    private String classroomName;
    //relationship with section and classroom o propiedad
}
