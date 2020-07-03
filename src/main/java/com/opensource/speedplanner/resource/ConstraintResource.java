package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConstraintResource {

    private Long id;
    private String startTime;
    private int numberOfHours;
    private String professorName;

}
