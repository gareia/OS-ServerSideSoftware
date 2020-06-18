package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.SectionSchedule;
import com.opensource.speedplanner.repository.SectionScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SectionScheduleServiceImpl implements SectionScheduleService{
    @Autowired
    private SectionScheduleRepository sectionScheduleRepository;

    @Override
    public SectionSchedule createSectionSchedule(SectionSchedule sectionSchedule) {
        return sectionScheduleRepository.save(sectionSchedule);
    }

    @Override
    public SectionSchedule getSectionScheduleById(Long sectionScheduleId) {
        return sectionScheduleRepository.findById(sectionScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("SectionSchedule", "Id", sectionScheduleId));
    }

    @Override
    public SectionSchedule updateSectionSchedule(Long sectionScheduleId, SectionSchedule sectionScheduleDetails) {
        return sectionScheduleRepository.findById(sectionScheduleId).map(sectionSchedule-> {
            sectionSchedule.setDay(sectionSchedule.getDay());
            sectionSchedule.setStartTime(sectionSchedule.getStartTime());
            sectionSchedule.setEndTime(sectionSchedule.getEndTime());
            sectionSchedule.setNumberOfHours(sectionSchedule.getNumberOfHours());
            return sectionScheduleRepository.save(sectionSchedule);
        }).orElseThrow(() -> new ResourceNotFoundException("SectionSchedule", "Id", sectionScheduleId));
    }

    @Override
    public ResponseEntity<?> deleteSectionSchedule(Long sectionScheduleId) {
        SectionSchedule sectionSchedule = sectionScheduleRepository.findById(sectionScheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("SectionSchedule", "Id", sectionScheduleId));
        sectionScheduleRepository.delete(sectionSchedule);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<SectionSchedule> getAllSectionSchedule(Pageable pageable) {
        return sectionScheduleRepository.findAll(pageable);
    }
}

