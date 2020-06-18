package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.SectionSchedule;
import com.opensource.speedplanner.resource.SaveSectionScheduleResource;
import com.opensource.speedplanner.resource.SectionScheduleResource;
import com.opensource.speedplanner.service.SectionScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "section schedules", description = "Section schedules API")
@RestController
@RequestMapping("/api")
public class SectionScheduleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SectionScheduleService sectionScheduleService;

    @Operation(summary = "Create Section schedule", description = "Create a Section schedule by Section Id " +
            "and given resource", tags = {"section schedules"})
    @PostMapping("/sectionSchedules")
    public SectionScheduleResource createSectionSchedule(@Valid @RequestBody SaveSectionScheduleResource resource) {
        return convertToResource(sectionScheduleService.createSectionSchedule(convertToEntity(resource)));
    }

    @Operation(summary = "Get Section schedule by Id and Section Id", description = "Get a Section schedule " +
            "by specifying Id and Section Id", tags = {"section schedules"})
    @GetMapping("/sectionSchedules/{id}")
    public SectionScheduleResource getSectionScheduleById(@PathVariable(name = "id") Long sectionScheduleId) {
        return convertToResource(sectionScheduleService.getSectionScheduleById(sectionScheduleId));
    }

    @Operation(summary = "Update Section schedule", description = "Update a Section schedule by specifying Id, " +
            "Section Id and given resource", tags = {"section schedules"})
    @PutMapping("/sectionSchedules/{id}")
    public SectionScheduleResource updateSectionSchedule(@PathVariable(name = "id") Long sectionScheduleId,
                                                         @Valid @RequestBody SaveSectionScheduleResource resource) {
        return convertToResource(sectionScheduleService.updateSectionSchedule(sectionScheduleId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Section schedule", description = "Delete a Section schedule by specifying Id " +
            "and Section Id", tags = {"section schedules"})
    @DeleteMapping("/sectionSchedules/{id}")
    public ResponseEntity<?> deleteSectionSchedules(@PathVariable(name = "id") Long sectionSchedulesId) {
        return sectionScheduleService.deleteSectionSchedule(sectionSchedulesId);
    }

    @Operation(summary = "Get All Section schedules by Section Id", description = "Get All Section schedules " +
            "by Pages and Section Id", tags = {"section schedules"})
    @GetMapping("/sectionSchedules")
    public Page<SectionScheduleResource> getAllSectionSchedule(Pageable pageable) {
        List<SectionScheduleResource> sectionSchedule = sectionScheduleService.getAllSectionSchedule(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int sectionSchedulesCount = sectionSchedule.size();
        return new PageImpl<>(sectionSchedule, pageable, sectionSchedulesCount);
    }

    private SectionSchedule convertToEntity(SaveSectionScheduleResource resource) { return mapper.map(resource, SectionSchedule.class); }

    private SectionScheduleResource convertToResource(SectionSchedule entity) { return mapper.map(entity, SectionScheduleResource.class); }
}

