package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.LearningProgram;
import com.opensource.speedplanner.resource.*;
import com.opensource.speedplanner.service.LearningProgramService;
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

@Tag(name="learning programs", description = "Learning programs API")
@RestController
@RequestMapping("/api/educationProviders/{educationProviderId}/learningPrograms")
public class LearningProgramController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private LearningProgramService learningProgramService;

    @Operation(summary = "Create Learning Program", description = "Create a LearningProgram by Education provider Id " +
            "and given resource", tags = {"learning programs"})
    @PostMapping //Agrega nombres de carreras iguales apesar de @column unique en saveResource
    public LearningProgramResource createLearningProgram(@PathVariable Long educationProviderId,
                                                         @Valid @RequestBody SaveLearningProgramResource resource){
        LearningProgram learningProgram = convertToEntity(resource);
        return convertToResource(learningProgramService.createLearningProgram(educationProviderId, learningProgram));
    }
    @Operation(summary = "Get All Learning programs by Education Provider Id", description = "Get All Learning programs " +
            "by Pages and Education provider Id", tags = {"learning programs"})
    @GetMapping
    public Page<LearningProgramResource> getAllLearningProgramsByEducationProviderId(
                                                                        @PathVariable Long educationProviderId,
                                                                        Pageable pageable){
        Page<LearningProgram> learningPrograms = learningProgramService.
                getAllLearningProgramsByEducationProvider(educationProviderId, pageable);
        List<LearningProgramResource> resources = learningPrograms.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Learning program by Id and Education Provider Id", description = "Get a Learning program " +
            "by specifying Id and Education Provider Id", tags = {"learning programs"})
    @GetMapping("/{learningProgramId}")
    public LearningProgramResource getLearningProgramByIdAndEducationProviderId(@PathVariable Long educationProviderId,
                                                                                @PathVariable Long learningProgramId){
        return convertToResource(learningProgramService.getLearningProgramByIdAndEducationProviderId(educationProviderId, learningProgramId));
    }
    @PostMapping("/learning programs/{learningProgramId}/courses/{courseId}")
    public LearningProgramResource assignLearningProgramCourse(@PathVariable(name = "learningProgramId") Long learningProgramId,
                                                   @PathVariable(name = "courseId") Long courseId) {
        return convertToResource(learningProgramService.assignLearningProgramCourse(learningProgramId, courseId));
    }
    @DeleteMapping("/learning programs/{learningProgramId}/courses/{courseId}")
    public LearningProgramResource unassignLearningProgramCourse(@PathVariable(name = "learningProgramId") Long learningProgramId,
                                             @PathVariable(name = "courseId") Long courseId) {

        return convertToResource(learningProgramService.unassignLearningProgramCourse(learningProgramId, courseId));
    }
    @GetMapping("/courses/{courseId}/learningPrograms")
    public Page<LearningProgramResource> getAllLearningProgramByCourseId(@PathVariable(name = "courseId") Long courseId, Pageable pageable) {
        Page<LearningProgram> learningProgramPage = learningProgramService.getAllLearningProgramByCourseId(courseId, pageable);
        List<LearningProgramResource> learningPrograms = learningProgramPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(learningPrograms, pageable, learningPrograms.size());
    }

    @Operation(summary = "Update Learning program", description = "Update a Learning program by specifying Id, " +
            "Education Provider Id and given resource", tags = {"learning programs"})
    @PutMapping("{learningProgramId}")
    public LearningProgramResource updateLearningProgram(@PathVariable Long educationProviderId,
                                                         @PathVariable Long learningProgramId,
                                                         @Valid @RequestBody SaveLearningProgramResource resource){
        LearningProgram learningProgram = convertToEntity(resource);
        return convertToResource(learningProgramService.updateLearningProgram(educationProviderId,
                learningProgramId, learningProgram));
    }

    @Operation(summary = "Delete Learning program", description = "Delete a Learning program by specifying Id " +
            "and Education Provider Id", tags = {"learning programs"})
    @DeleteMapping("{learningProgramId}")
    public ResponseEntity<?> deleteLearningProgram(@PathVariable Long educationProviderId,
                                                   @PathVariable Long learningProgramId){
        return learningProgramService.deleteLearningProgram(educationProviderId, learningProgramId);
    }


    private LearningProgram convertToEntity(SaveLearningProgramResource resource){
        return mapper.map(resource, LearningProgram.class);
    }
    private LearningProgramResource convertToResource(LearningProgram entity){
        return mapper.map(entity, LearningProgramResource.class);
    }
}
