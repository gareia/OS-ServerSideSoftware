package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.HourRangeConstraint;
import com.opensource.speedplanner.repository.HourRangeConstraintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HourRangeConstraintServiceImpl implements HourRangeConstraintService{

    @Autowired
    public HourRangeConstraintRepository hourRangeConstraintRepository;

    @Override
    public HourRangeConstraint createHourRangeConstraint(HourRangeConstraint hourRangeConstraint) {
        hourRangeConstraint.setType(1);
        //TODO: hourRangeConstraint.setActiveFilter(false);
        return hourRangeConstraintRepository.save(hourRangeConstraint);
    }

    @Override
    public HourRangeConstraint getHourRangeConstraintById(Long hourRangeConstraintId) {
        return hourRangeConstraintRepository.findById(hourRangeConstraintId)
                .orElseThrow(()->new ResourceNotFoundException("HourRangeConstraint", "Id", hourRangeConstraintId));
    }

    @Override
    public Page<HourRangeConstraint> getAllHourRangeConstraints(Pageable pageable) {
        return hourRangeConstraintRepository.findAll(pageable);
    }

    @Override
    public HourRangeConstraint updateHourRangeConstraint(Long hourRangeConstraintId, HourRangeConstraint details) {
        HourRangeConstraint hourRangeConstraint = getHourRangeConstraintById(hourRangeConstraintId);
        //throws an exception if is not found
        hourRangeConstraint.setNumberOfHours(details.numberOfHours);
        //TODO: add setters of date times
        return hourRangeConstraintRepository.save(hourRangeConstraint);
    }

    @Override
    public ResponseEntity<?> deleteHourRangeConstraint(Long hourRangeConstraintId) {
        HourRangeConstraint hourRangeConstraint = getHourRangeConstraintById(hourRangeConstraintId);
        //throws an exception if is not found
        hourRangeConstraintRepository.delete(hourRangeConstraint);
        return ResponseEntity.ok().build();
    }
}
