package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.resource.*;
import com.opensource.speedplanner.service.ClassroomService;
import com.opensource.speedplanner.service.EducationProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name="classrooms", description = "Classroom API")
@RestController
@RequestMapping("/api")
public class ClassroomController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ClassroomService classroomService;

    @Operation(summary = "Get Classrooms providers", description = "Get All Classrooms providers by Pages",
            tags = {"classrooms"})
    @GetMapping("/classrooms")
    public Page<ClassroomResource> getAllClassrooms(Pageable pageable){
        Page<Classroom> classrooms = classroomService.getAllClassroom(pageable);
        List<ClassroomResource> resources = classrooms.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Classroom by Id", description = "Get a Classroom by specifying Id", tags = { "classrooms" })
    @GetMapping("/classrooms/{id}")
    public ClassroomResource getClassroomById(
            @Parameter(description="Classroom Id")
            @PathVariable(name = "id") Long classroomId) {
        return convertToResource(classroomService.getClassroomById(classroomId));
    }
    @Operation(summary = "Create Classroom", description = "Create a Classroom by given resource", tags = {"classrooms"})
    @PostMapping("/classrooms")
    public ClassroomResource createCourse(@Valid @RequestBody SaveClassroomResource resource)  {
        Classroom classroom = convertToEntity(resource);
        return convertToResource(classroomService.createClassroom(classroom));
    }
    @Operation(summary = "Update Classroom", description = "Update a Classroom by specifying Id and given resource", tags = {"classrooms"})
    @PutMapping("/classrooms/{id}")
    public ClassroomResource updateClassroom(@PathVariable(name = "id") Long classroomId, @Valid @RequestBody SaveClassroomResource resource) {
        Classroom classroom = convertToEntity(resource);
        return convertToResource(classroomService.updateClassroom(classroomId, classroom));
    }
    @Operation(summary = "Delete Classroom", description = "Delete a Classroom by specifying Id", tags = {"classrooms"})
    @DeleteMapping("/classrooms/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable(name = "id") Long classroomId) {
        return classroomService.deleteClassroom(classroomId);
    }
    @GetMapping("/courses/{courseId}/classrooms")
    public Page<ClassroomResource> getAllClassroomByCourseId(@PathVariable(name = "courseId") Long courseId, Pageable pageable) {
        Page<Classroom> classroomsPage = classroomService.getAllClassroomByCourseId(courseId, pageable);
        List<ClassroomResource> resources = classroomsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @PostMapping("/classrooms/{classroomId}/courses/{courseId}")
    public ClassroomResource assignClassroomCourse(@PathVariable(name = "classroomId") Long classroomId,
                                      @PathVariable(name = "courseId") Long courseId) {
        return convertToResource(classroomService.assignClassroomCourse(classroomId, courseId));
    }
    @DeleteMapping("/classrooms/{classroomId}/course/{courseId}")
    public ClassroomResource unassignPostTag(@PathVariable(name = "classroomId") Long classroomId,
                                        @PathVariable(name = "courseId") Long courseId) {

        return convertToResource(classroomService.unassignClassroomCourse(classroomId, courseId));
    }
    @GetMapping("/educationProviders/{educationProviderId}/classroom/{classroomId}")
    public ClassroomResource getClassroomByIdAndEducationProviderId(@PathVariable(name = "educationProviderId") Long educationProviderId,
                                                   @PathVariable(name = "classroomId") Long classroomId) {
        return convertToResource(classroomService.getClassroomByIdAndEducationProviderId(educationProviderId, classroomId));
    }


    private Classroom convertToEntity(SaveClassroomResource resource){
        return mapper.map(resource, Classroom.class);
    }
    private ClassroomResource convertToResource(Classroom entity){
        return mapper.map(entity, ClassroomResource.class);
    }
}
