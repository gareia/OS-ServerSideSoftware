package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearningProgramResource {

    private Long id;
    private String type; //CarreraUniv CarreraTecn
    private String name; //IngSoftware Mecanico
    private Long numberOfCourses;
    private Long educationProviderId;

}
