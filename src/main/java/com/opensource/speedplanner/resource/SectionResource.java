package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionResource {

    private Long id;
    private String sectionName;
    private String venue; //sede
    private int vacancy;
    private int numberOfHours;
    private String professorName; //for now
    //o relacion con professor o prop professor
    private Long courseId;
    private int registeredStudents;
}
