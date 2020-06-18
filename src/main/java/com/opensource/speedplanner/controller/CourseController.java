package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.resource.CourseResource;
import com.opensource.speedplanner.resource.SaveCourseResource;
import com.opensource.speedplanner.service.CourseService;
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

@Tag(name = "courses", description = "Courses API")
@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CourseService courseService;

    @Operation(summary = "Get Courses", description = "Get All Courses by Pages", tags = { "courses" })
    @GetMapping("/courses")
    public Page<CourseResource> getAllCourse(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Course> coursePage = courseService.getAllCourse(pageable);
        List<CourseResource> resources = coursePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Course by Id", description = "Get a Course by specifying Id", tags = { "courses" })
    @GetMapping("/courses/{id}")
    public CourseResource getCourseById(
            @Parameter(description="Course Id")
            @PathVariable(name = "id") Long courseId) {
        return convertToResource(courseService.getCourseById(courseId));
    }
/*
    @Operation(summary = "Get Courses by ClassroomId", description = "Get all Courses by pages and specifying Classroom Id", tags = { "courses" })
    @GetMapping("/classrooms/{classroomId}/courses")
    public Page<CourseResource> getAllCourseByClassroomId(@PathVariable(name = "classroomId") Long classroomId, Pageable pageable) {
        Page<Course> coursesPage = courseService.getAllCourseByClassroomId(classroomId, pageable);
        List<CourseResource> resources = coursesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
*/
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


    private CourseResource convertToResource(Course entity) {
        return mapper.map(entity, CourseResource.class);
    }

    private Course convertToEntity(SaveCourseResource resource) {
        return mapper.map(resource, Course.class);
    }
}
