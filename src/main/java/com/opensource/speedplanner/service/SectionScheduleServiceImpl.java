package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.SectionSchedule;
import com.opensource.speedplanner.repository.SectionRepository;
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

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public SectionSchedule createSectionSchedule(Long sectionId, SectionSchedule sectionSchedule) {
        return sectionRepository.findById(sectionId).map(section -> {
            sectionSchedule.setSection(section);
            return sectionScheduleRepository.save(sectionSchedule);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Section", "Id", sectionId));
    }

    @Override
    public SectionSchedule getSectionScheduleByIdAndSectionId(Long sectionId, Long sectionScheduleId) {
        return sectionScheduleRepository.findByIdAndSectionId(sectionScheduleId, sectionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SectionSchedule not found with Id " + sectionScheduleId +
                                " and SectionId " + sectionId));
    }

    @Override
    public SectionSchedule updateSectionSchedule(Long sectionId, Long sectionScheduleId, SectionSchedule sectionScheduleDetails) {
        if(!sectionRepository.existsById(sectionId))
            throw new ResourceNotFoundException("Section", "Id", sectionId);

        return sectionScheduleRepository.findById(sectionScheduleId).map(sectionSchedule-> {
            sectionSchedule.setDay(sectionSchedule.getDay());
            sectionSchedule.setStartTime(sectionSchedule.getStartTime());
            sectionSchedule.setEndTime(sectionSchedule.getEndTime());
            sectionSchedule.setNumberOfHours(sectionSchedule.getNumberOfHours());
            return sectionScheduleRepository.save(sectionSchedule);
        }).orElseThrow(() -> new ResourceNotFoundException("SectionSchedule", "Id", sectionScheduleId));
    }

    @Override
    public ResponseEntity<?> deleteSectionSchedule(Long sectionId, Long sectionScheduleId) {
        return sectionScheduleRepository.findByIdAndSectionId(sectionScheduleId, sectionId).map(sectionSchedule -> {
            sectionScheduleRepository.delete(sectionSchedule);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Section schedule not found with Id " + sectionScheduleId + " and SectionId " + sectionId));
    }

    @Override
    public Page<SectionSchedule> getAllSectionSchedule(Pageable pageable) {
        return sectionScheduleRepository.findAll(pageable);
    }

    @Override
    public Page<SectionSchedule> getAllSectionSchedulesBySectionId(Long sectionId, Pageable pageable) {
        return  sectionScheduleRepository.findBySectionId(sectionId , pageable);
    }
}

