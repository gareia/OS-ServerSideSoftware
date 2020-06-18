package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.repository.ClassroomRepository;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.EducationProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Classroom> getAllClassroom(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    @Override
    public Page<Classroom> getAllClassroomByCourseId(Long courseId, Pageable pageable) {
        return courseRepository.findById(courseId).map(course -> {
            List<Classroom> classrooms = course.getClassrooms();
            int classroomCount = classrooms.size();
            return new PageImpl<>(classrooms, pageable, classroomCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Classroom assignClassroomCourse(Long classroomId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return classroomRepository.findById(classroomId).map(classroom -> {
            if(!classroom.getCourses().contains(course)) {
                classroom.getCourses().add(course);
                return classroomRepository.save(classroom);
            }
            return classroom;
        }).orElseThrow(() -> new ResourceNotFoundException("Classroom", "Id", classroomId));
    }

    @Override
    public Classroom unassignClassroomCourse(Long classroomId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return classroomRepository.findById(classroomId).map(classroom -> {
            classroom.getCourses().remove(course);
            return classroomRepository.save(classroom);
        }).orElseThrow(() -> new ResourceNotFoundException("Classroom", "Id", classroomId));
    }

    @Override
    public Classroom getClassroomById(Long classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(()->new ResourceNotFoundException("Classroom", "Id", classroomId));
    }

    @Override
    public Classroom createClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom updateClassroom(Long classroomId, Classroom classroomDetails) {
        Classroom classroom = getClassroomById(classroomId);
        //throw exception if educationProviderId isnt found
        classroom.setClassroomName(classroomDetails.getClassroomName());
        classroom.setCapacity(classroomDetails.getCapacity());
        classroom.setType(classroomDetails.getType());
        return classroomRepository.save(classroom);
    }

    @Override
    public ResponseEntity<?> deleteClassroom(Long classroomId) {
        Classroom classroom = getClassroomById(classroomId);
        //throw exception if educationProviderId isnt found
        classroomRepository.delete(classroom);
        return ResponseEntity.ok().build();
    }
}
