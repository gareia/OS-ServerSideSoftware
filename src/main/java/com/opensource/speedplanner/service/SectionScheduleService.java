package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.SectionSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SectionScheduleService {
    SectionSchedule createSectionSchedule(SectionSchedule sectionSchedule);
    SectionSchedule getSectionScheduleById(Long sectionScheduleId);
    SectionSchedule updateSectionSchedule(Long sectionScheduleId, SectionSchedule sectionScheduleDetails);
    ResponseEntity<?> deleteSectionSchedule(Long sectionScheduleId);
    Page<SectionSchedule> getAllSectionSchedule(Pageable pageable);
}
