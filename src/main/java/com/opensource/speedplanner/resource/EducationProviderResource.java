package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EducationProviderResource {

    private Long id;
    private String name;
    private int numberOfCareers;
    private String currentPeriodCode;


}
