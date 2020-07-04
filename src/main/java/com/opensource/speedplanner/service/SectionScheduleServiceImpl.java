package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.SectionSchedule;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.SectionRepository;
import com.opensource.speedplanner.repository.SectionScheduleRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SectionScheduleServiceImpl implements SectionScheduleService {
    @Autowired
    private SectionScheduleRepository sectionScheduleRepository;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private InscriptionProcessRepository inscriptionProcessRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public SectionSchedule createSectionSchedule(Long sectionId, SectionSchedule sectionSchedule) {
        return sectionRepository.findById(sectionId).map(section -> {
            sectionSchedule.setSection(section);
            return sectionScheduleRepository.save(sectionSchedule);
        }).orElseThrow(() -> new ResourceNotFoundException("Section", "Id", sectionId));
    }

    @Override
    public Page<SectionSchedule> getAllSectionSchedulesBySectionId(Long sectionId, Pageable pageable) {
        return sectionScheduleRepository.findBySectionId(sectionId, pageable);
    }
/*
    @Override
    public List<SectionSchedule> getAllSectionSchedulesByConstraint(Long userId){

        Long inscriptionProcessId = userRepository.findById(userId).get().getInscriptionProcess().getId();
        List<SectionSchedule> results = new ArrayList<>();

        inscriptionProcessRepository.findById(inscriptionProcessId).get().getSections().stream()
        .map(section -> results.addAll(section.getSectionSchedules()));

        return results;
    }*/
}
