package com.opensource.speedplanner.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "hour_range_constraints")
@PrimaryKeyJoinColumn(name = "hour_range_constraint_id")
@Getter
@Setter
public class HourRangeConstraint extends Constraint {

    public int numberOfHours;

    //public date startTime;
    //public date endTime;
}
