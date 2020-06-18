package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PeriodResource {
    private int code;
    private Date startDate;
    private Date endDate;

}
