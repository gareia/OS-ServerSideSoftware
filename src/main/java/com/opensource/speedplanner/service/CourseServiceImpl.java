package com.opensource.speedplanner.service;

//import com.opensource.speedplanner.exception.ResourceNotFoundException;
//import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Course;
//import com.opensource.speedplanner.repository.ClassroomRepository;
import com.opensource.speedplanner.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    //@Autowired
    //private ClassroomRepository classroomRepository;

    @Override
    public Page<Course> getAllCourse(Pageable pageable) {
        return courseRepository.findAll(pageable);

    }
/*
    @Override
    public Page<Course> getAllCourseByClassroomId(Long classroomId, Pageable pageable) {
        return classroomRepository.findById(classroomId).map(classroom -> {
            List<Course> courses = classroom.getRequisites();
            int courseCount = courses.size();
            return new PageImpl<>(courses, pageable, courseCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Classroom", "Id",classroomId));
    }*/

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course courseDetails) {
        return courseRepository.findById(courseId).map(course -> {
            course.setCode (courseDetails.getCode());
            return courseRepository.save(course);
        }).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }


}
