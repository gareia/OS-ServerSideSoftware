package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResource{

     private Long id;
     private String code;
     private String name;
     private boolean isOptional;
     private boolean isVirtual;
     private int semester;
     private int credits;
     private Long totalNumberOfStudents;
     //private Long education_provider_id;
}