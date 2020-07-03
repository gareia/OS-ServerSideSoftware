package com.opensource.speedplanner.service;


//import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CourseService {
    Course createCourse(Long educationProviderId, Course course);
    Course getCourseById(Long courseId);
    Course getCourseByIdAndEducationProviderId(Long educationProviderId, Long courseId);
    Page<Course> getAllCoursesByEducationProviderId(Long educationProviderId, Pageable pageable);
    List<Course> getAllCoursesByProfileId(Long profileId);
    List<Course> getAllCoursesByUserId(Long userId);

    Course updateCourse(Long educationProviderId, Long courseId, Course details);
    ResponseEntity<?> deleteCourse(Long educationProviderId, Long courseId);
}

