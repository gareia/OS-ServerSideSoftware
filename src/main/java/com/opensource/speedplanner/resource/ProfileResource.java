package com.opensource.speedplanner.resource;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResource {

    private Long id;
    private String names;
    private String lastNames;
    private boolean gender;
    private int semester;//not required
    private Long idNumber; //dni
    private Long educationProviderId;
    private Long learningProgramId;
}
