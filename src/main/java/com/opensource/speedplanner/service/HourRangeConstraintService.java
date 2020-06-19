package com.opensource.speedplanner.service;


import com.opensource.speedplanner.model.HourRangeConstraint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface HourRangeConstraintService {

    HourRangeConstraint createHourRangeConstraint(HourRangeConstraint hourRangeConstraint);
    HourRangeConstraint getHourRangeConstraintById(Long hourRangeConstraintId);
    Page<HourRangeConstraint> getAllHourRangeConstraints(Pageable pageable);
    HourRangeConstraint updateHourRangeConstraint(Long hourRangeConstraintId, HourRangeConstraint details);
    ResponseEntity<?> deleteHourRangeConstraint(Long hourRangeConstraintId);

}
