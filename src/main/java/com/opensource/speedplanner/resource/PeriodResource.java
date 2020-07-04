package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeriodResource {
    private Long id;
    private String code;
    private String startDate;
    private String endDate;
}
