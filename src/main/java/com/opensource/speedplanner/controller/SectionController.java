package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Section;
import com.opensource.speedplanner.model.SectionRequest;
import com.opensource.speedplanner.resource.CourseResource;
import com.opensource.speedplanner.resource.SaveSectionResource;
import com.opensource.speedplanner.resource.SectionResource;
import com.opensource.speedplanner.service.SectionService;
import com.opensource.speedplanner.service.SectionServiceImpl;
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

@Tag(name = "sections", description = "Sections API")
@RestController
@RequestMapping("/api")
public class SectionController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SectionService sectionService;


    @GetMapping("/sections")
    public Page<SectionResource> getAllSections(Pageable pageable){
        Page<Section> sections = sectionService.getAllSections(pageable);
        List<SectionResource> resources = sections.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }

    @GetMapping("/courses/{courseId}/sections")
    public Page<SectionResource> getAllSectionsByCourseId(
            @PathVariable(name = "courseId") Long courseId,
            Pageable pageable) {
        Page<Section> sectionPage = sectionService.getAllSectionsByCourseId(courseId, pageable);
        List<SectionResource> resources = sectionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/courses/{courseId}/sections/{sectionId}")
    public SectionResource getSectionByIdAndCourseId(@PathVariable(name = "courseId") Long courseId,
                                                   @PathVariable(name = "sectionId") Long sectionId) {
        return convertToResource(sectionService.getSectionByIdAndCourseId(courseId, sectionId));
    }

    @Operation(summary = "Create Section", description = "Create a Section by Course Id and given resource",
            tags = {"sections"})
    @PostMapping("/courses/{courseId}/sections")
    public  SectionResource createSection(@PathVariable(name = "courseId")Long courseId,
                                          @Valid @RequestBody SaveSectionResource resource
                                          ){
        return convertToResource(sectionService.createSection(courseId, convertToEntity(resource)));
    }

    @Operation(summary = "Update Section", description = "Update a Section by specifying Id, " +
            "Course Id and given resource", tags = {"sections"})
    @PutMapping("/courses/{courseId}/sections/{sectionId}")
    public SectionResource updateSection(@PathVariable(name = "courseId")Long courseId,
                                         @PathVariable(name = "sectionId") Long sectionId,
                                         @Valid @RequestBody SaveSectionResource resource){
        return convertToResource(sectionService.updateSection(courseId,sectionId,convertToEntity(resource)));
    }

    /*@DeleteMapping("/users/{userId}/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "userId")Long userId,
                                        @PathVariable(name = "roleId")Long roleId
                                        ){
        return roleService.deleteRole(userId, roleId);
    }*/

    @Operation(summary = "Delete Section", description = "Delete a Section by specifying Id " +
            "and Course Id", tags = {"sections"})
    @DeleteMapping("/courses/{courseId}/sections/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable(name = "courseId") Long courseId,
                                           @PathVariable(name = "sectionId") Long sectionId){
        return sectionService.deleteSection(courseId,sectionId);
    }

    private Section convertToEntity(SaveSectionResource resource){
        return mapper.map(resource, Section.class);
    }

    private SectionResource convertToResource(Section entity){
        return mapper.map(entity, SectionResource.class);
    }
}
