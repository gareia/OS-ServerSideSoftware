package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Profile;
import com.opensource.speedplanner.model.SectionSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SectionScheduleService {
    SectionSchedule createSectionSchedule(Long sectionId, SectionSchedule sectionSchedule);
    SectionSchedule getSectionScheduleByIdAndSectionId(Long sectionId, Long sectionScheduleId);
    SectionSchedule updateSectionSchedule(Long sectionId, Long sectionScheduleId, SectionSchedule sectionScheduleDetails);
    ResponseEntity<?> deleteSectionSchedule(Long sectionId, Long sectionScheduleId);
    Page<SectionSchedule> getAllSectionSchedule(Pageable pageable);
    Page<SectionSchedule> getAllSectionSchedulesBySectionId(Long sectionId , Pageable pageable);
}
