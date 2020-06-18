package com.opensource.speedplanner.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter

public class SubscriptionResource {
    private Long id;
    private Double cost;
    private Date startDate;
    private Date endDate;
}