package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.SectionSchedule;
import com.opensource.speedplanner.resource.SaveSectionScheduleResource;
import com.opensource.speedplanner.resource.SectionScheduleResource;
import com.opensource.speedplanner.service.SectionScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class SectionScheduleController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SectionScheduleService sectionScheduleService;

    @PostMapping("/sections/{sectionId}/sectionSchedules")
    public SectionScheduleResource createSectionSchedule(@PathVariable Long sectionId,
                                                         @Valid @RequestBody SaveSectionScheduleResource resource){
        SectionSchedule sectionSchedule = convertToEntity(resource);
        return convertToResource(sectionScheduleService.createSectionSchedule(sectionId, sectionSchedule));
    }

    @GetMapping("/sections/{sectionId}/sectionSchedules")
    public Page<SectionScheduleResource> getAllSectionSchedulesBySectionId(@PathVariable Long sectionId, Pageable pageable){

        Page<SectionSchedule> sectionSchedules = sectionScheduleService.getAllSectionSchedulesBySectionId(sectionId, pageable);
        List<SectionScheduleResource> resources = sectionSchedules.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private SectionSchedule convertToEntity(SaveSectionScheduleResource resource){
        return mapper.map(resource, SectionSchedule.class);
    }
    private SectionScheduleResource convertToResource(SectionSchedule entity){
        return mapper.map(entity, SectionScheduleResource.class);
    }

}
