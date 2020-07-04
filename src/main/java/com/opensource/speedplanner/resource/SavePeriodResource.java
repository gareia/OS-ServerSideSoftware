package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SavePeriodResource {

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String code;

    @NotBlank
    @NotNull
    private String startDate;

    @NotBlank
    @NotNull
    private String endDate;
}
