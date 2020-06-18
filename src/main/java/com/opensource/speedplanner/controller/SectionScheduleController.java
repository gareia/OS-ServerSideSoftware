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
    @PostMapping("/sections/{sectionId}/sectionSchedules")
    public SectionScheduleResource createSectionSchedule(@PathVariable(name = "sectionId") Long sectionId,
                                                         @Valid @RequestBody SaveSectionScheduleResource resource) {
        return convertToResource(sectionScheduleService.createSectionSchedule(sectionId, convertToEntity(resource)));
    }

    @Operation(summary = "Get All Section schedules by Section Id", description = "Get All Section schedules " +
            "by specifying Section Id", tags = {"section schedules"})
    @GetMapping("/sections/{sectionId}/sectionSchedules")
    public Page<SectionScheduleResource> getAllSectionSchedulesBySectionId(
            @PathVariable(name = "sectionId") Long sectionId,
            Pageable pageable) {
        Page<SectionSchedule> sectionSchedulesPage = sectionScheduleService.getAllSectionSchedulesBySectionId(sectionId, pageable);
        List<SectionScheduleResource> resources = sectionSchedulesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Section schedule by Id and Section Id", description = "Get a Section schedule " +
            "by specifying Id and Section Id", tags = {"section schedules"})
    @GetMapping("/sections/{sectionId}/sectionSchedules/{sectionScheduleId}")
    public SectionScheduleResource getSectionScheduleByIdAndSectionId(@PathVariable(name = "sectionId") Long sectionId,
                                                          @PathVariable(name = "sectionScheduleId") Long sectionScheduleId) {
        return convertToResource(sectionScheduleService.getSectionScheduleByIdAndSectionId(sectionId, sectionScheduleId));
    }

    @Operation(summary = "Update Section schedule", description = "Update a Section schedule by specifying Id, " +
            "Section Id and given resource", tags = {"section schedules"})
    @PutMapping("/sections/{sectionId}/sectionSchedules/{sectionScheduleId}")
    public SectionScheduleResource updateSectionSchedule(@PathVariable(name = "sectionId") Long sectionId,
    @PathVariable(name = "sectionScheduleId") Long sectionScheduleId,
    @Valid @RequestBody SaveSectionScheduleResource resource) {
        return convertToResource(sectionScheduleService.updateSectionSchedule(sectionId, sectionScheduleId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Section schedule", description = "Delete a Section schedule by specifying Id " +
            "and Section Id", tags = {"section schedules"})
    @DeleteMapping("/sections/{sectionId}/sectionSchedules/{sectionScheduleId}")
    public ResponseEntity<?> deleteSectionSchedules(@PathVariable(name = "sectionId") Long sectionId,
                                                    @PathVariable(name = "sectionScheduleId") Long sectionScheduleId){
        return sectionScheduleService.deleteSectionSchedule(sectionId, sectionScheduleId);
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

