package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter

public class SaveSubscriptionResource {

    @NotBlank
    @NotNull
    private Double cost;

    @NotBlank
    @NotNull
    private Date startDate;

    @NotBlank
    @NotNull
    private Date endDate;
}
