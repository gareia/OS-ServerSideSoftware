package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SavePeriodResource {

    @NotBlank
    @NotNull
    private int code;

    @NotBlank
    @NotNull
    private Date startDate;

    @NotBlank
    @NotNull
    private Date endDate;
}

