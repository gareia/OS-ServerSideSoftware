package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class SaveStatisticResource {
    @NotBlank
    @NotNull
    private Double womenPercentageInPeriod;

    @NotBlank
    @NotNull
    private Double menPercentageInPeriod;

    @NotBlank
    @NotNull
    private int RegisteredStudentsInPeriod;
}
