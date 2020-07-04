package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.resource.CourseResource;
import com.opensource.speedplanner.resource.SaveCourseResource;
import com.opensource.speedplanner.service.CourseService;
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

@Tag(name = "courses", description = "Courses API")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CourseService courseService;

    @PostMapping("/educationProviders/{educationProviderId}/courses")
    public CourseResource createCourse(@PathVariable Long educationProviderId, @Valid @RequestBody SaveCourseResource resource) {

        Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(educationProviderId, course));
    }

    @GetMapping("/educationProviders/{educationProviderId}/courses")
    public Page<CourseResource> getAllCoursesByEducationProviderId(@PathVariable Long educationProviderId, Pageable pageable){
        Page<Course> courses = courseService.getAllCoursesByEducationProviderId(educationProviderId , pageable);
        List<CourseResource> resources = courses.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/profiles/{profileId}/courses")
    public Page<CourseResource> getAllCoursesByProfileId(@PathVariable Long profileId, Pageable pageable){
        List<Course> courses = courseService.getAllCoursesByProfileId(profileId);
        List<CourseResource> resources = courses.stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/users/{userId}/courses")
    public Page<CourseResource> getAllCoursesByUserIdAndInscriptionProcessId(@PathVariable Long userId, Pageable pageable){
        List<Course> courses = courseService.getAllCoursesByUserId(userId);
        List<CourseResource> resources = courses.stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/educationProviders/{educationProviderId}/courses/{courseId}")
    public CourseResource getCourseByIdAndEducationProviderId(@PathVariable Long educationProviderId, @PathVariable Long courseId){

        return convertToResource(courseService.getCourseByIdAndEducationProviderId(educationProviderId, courseId));
    }

    @PutMapping("/educationProviders/{educationProviderId}/courses/{courseId}")
    public CourseResource updateCourse(@PathVariable Long educationProviderId, @PathVariable Long courseId,
                                       @Valid @RequestBody SaveCourseResource resource){
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(educationProviderId, courseId, course));
    }

    @DeleteMapping("/educationProviders/{educationProviderId}/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long educationProviderId, @PathVariable Long courseId){
        return courseService.deleteCourse(educationProviderId, courseId);
    }

    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }

    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }
}
/*
    @Operation(summary = "Get Courses by ClassroomId", description = "Get all Courses by pages and specifying Classroom Id", tags = { "courses" })
    @GetMapping("/classrooms/{classroomId}/courses")
    public Page<CourseResource> getAllCourseByClassroomId(@PathVariable(name = "classroomId") Long classroomId, Pageable pageable) {
        Page<Course> coursesPage = courseService.getAllCourseByClassroomId(classroomId, pageable);
        List<CourseResource> resources = coursesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Course", description = "Create a Course by given resource", tags = {"courses"})
    @PostMapping("/courses")
    public CourseResource createCourse(@Valid @RequestBody SaveCourseResource resource)  {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.createCourse(course));
    }

    @Operation(summary = "Update Course", description = "Update a Course by specifying Id and given resource", tags = {"classrooms"})
    @PutMapping("/courses/{id}")
    public CourseResource updateCourse(@PathVariable(name = "id") Long courseId, @Valid @RequestBody SaveCourseResource resource) {
        Course course = convertToEntity(resource);
        return convertToResource(courseService.updateCourse(courseId, course));
    }

    @Operation(summary = "Delete Course", description = "Delete a Course by specifying Id", tags = {"courses"})
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable(name = "id") Long courseId) {
        return courseService.deleteCourse(courseId);
    }



}
*/