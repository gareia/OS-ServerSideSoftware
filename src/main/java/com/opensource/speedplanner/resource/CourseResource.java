package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResource {
 public int id;
 public String code;
 public String name;
 public Long totalNumberOfStudents;
 public boolean isOptional;
 public boolean isVirtual;
 public int semester;
 public int numberOfCredits;
 public int credits;

}
