package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomResource {
    private int id;
    private String classroomName;
    private String type;
    private int capacity;
}
