package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResource {
 private int id;
 private String code;
 private String name;
 private Long totalNumberOfStudents;
 private boolean isOptional;
 private boolean isVirtual;
 private int semester;
 private int numberOfCredits;
 private int credits;

}
