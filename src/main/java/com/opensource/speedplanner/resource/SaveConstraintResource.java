package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class SaveConstraintResource {

    private String startTime;
    private int numberOfHours;
    private String professorName;

}
