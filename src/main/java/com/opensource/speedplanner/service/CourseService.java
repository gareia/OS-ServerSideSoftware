package com.opensource.speedplanner.service;


//import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseService {
    Page<Course> getAllCourse(Pageable pageable);
    Page<Course> getAllCourseByClassroomId(Long classroomId, Pageable pageable);
    Course getCourseById(Long courseId);
    Course createCourse(Course course);
    Course updateCourse(Long courseId, Course courseDetails);
    ResponseEntity<?> deleteCourse(Long courseId);
}

