package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectionResource {
    private Long id;
    private String sectionName;
    private String venue;
    private int registeredStudents;
    private int numberOfHours;
}
