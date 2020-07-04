package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.SectionSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectionScheduleService {

    SectionSchedule createSectionSchedule(Long sectionId, SectionSchedule sectionSchedule);
    Page<SectionSchedule> getAllSectionSchedulesBySectionId(Long sectionId, Pageable pageable);

    //List<SectionSchedule> getAllSectionSchedulesByConstraint(Long userId);
}
