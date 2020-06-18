package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class SectionScheduleResource {
    public String code;
    public String sectionName;
    public Date startTime;
    public Date endTime;
    public  int numberOfHours;
    public String day;
}
