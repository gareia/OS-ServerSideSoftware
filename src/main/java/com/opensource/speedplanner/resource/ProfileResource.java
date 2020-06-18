package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResource {
    private Long id;
    private String name;
    private String lastNames;
    private boolean gender;
    private int semester;
    private Long idNumber;
}
