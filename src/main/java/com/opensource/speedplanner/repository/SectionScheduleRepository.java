package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.SectionSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionScheduleRepository extends JpaRepository<SectionSchedule, Long> {
    Page<SectionSchedule> findBySectionId (Long sectionId, Pageable pageable);
    Optional<SectionSchedule> findByIdAndSectionId(Long id, Long userId);
}
