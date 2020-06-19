package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.HourRangeConstraint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourRangeConstraintRepository extends JpaRepository<HourRangeConstraint, Long> {
}
