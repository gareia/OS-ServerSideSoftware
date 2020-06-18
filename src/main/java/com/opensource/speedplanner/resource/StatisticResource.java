package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatisticResource {
    private Long id;
    private Double womenPercentageInPeriod;
    private Double menPercentageInPeriod;
    private int RegisteredStudentsInPeriod;

}