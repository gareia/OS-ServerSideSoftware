package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePossibleScheduleResource {

    @NotBlank
    @NotNull
    private int credits;

    @NotNull
    private boolean isFinal;
}
