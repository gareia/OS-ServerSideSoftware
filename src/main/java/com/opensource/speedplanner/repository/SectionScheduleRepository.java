package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.SectionSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionScheduleRepository extends JpaRepository<SectionSchedule, Long> {
}
