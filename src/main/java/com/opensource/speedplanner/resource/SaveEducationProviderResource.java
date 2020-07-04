package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveEducationProviderResource {
    @NotNull
    @NotBlank
    @Size(max = 40)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    private int numberOfCareers;

    @NotNull
    @NotBlank
    private String currentPeriodCode;
}
