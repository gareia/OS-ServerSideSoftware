package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Section;
import com.opensource.speedplanner.resource.SaveSectionResource;
import com.opensource.speedplanner.resource.SectionResource;
import com.opensource.speedplanner.service.SectionService;
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
@RequestMapping("/api")
public class SectionController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SectionService sectionService;

    @PostMapping("/courses/{courseId}/sections")
    public SectionResource createSection(@PathVariable Long courseId, @Valid @RequestBody SaveSectionResource resource){
        Section section = convertToEntity(resource);
        return convertToResource(sectionService.createSection(courseId, section));
    }

    @GetMapping("/courses/{courseId}/sections")
    public Page<SectionResource> getAllSectionsByCourseId(@PathVariable Long courseId, Pageable pageable){
        Page<Section> sections = sectionService.getAllSectionsByCourseId(courseId, pageable);
        List<SectionResource> resources = sections.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
/*
    @GetMapping("/courses/{courseId}/constraints/{constraintId}/sections")
    public Page<SectionResource> getAllSectionsByCourseId(@PathVariable Long courseId, @PathVariable Long constraintId, Pageable pageable) {
        //TODO:COMPLETAR
    }*/

    public Section convertToEntity(SaveSectionResource resource){
        return mapper.map(resource, Section.class);
    }
    public SectionResource convertToResource(Section entity){
        return mapper.map(entity, SectionResource.class);
    }
}
