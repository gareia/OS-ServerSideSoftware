package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Constraint;
import com.opensource.speedplanner.repository.ConstraintRepository;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.InscriptionProcessRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstraintServiceImpl implements ConstraintService{

    @Autowired
    ConstraintRepository constraintRepository;
    @Autowired
    InscriptionProcessRepository inscriptionProcessRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;


    @Override
    public Constraint createConstraint(Long courseId, Constraint constraint) {
        return courseRepository.findById(courseId).map(course->{
            constraint.setCourse(course);
            course.setConstraint(constraint);
            return constraintRepository.save(constraint);
        }).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Constraint getConstraintByCourseId(Long courseId){

        return courseRepository.findById(courseId).map(course->
            course.getConstraint()
        ).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Constraint updateConstraint(Long courseId, Constraint details) {

        Constraint constraint = courseRepository.findById(courseId).map(course -> course.getConstraint())
                .orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));

        constraint.setProfessorName(details.getProfessorName());
        constraint.setNumberOfHours(details.getNumberOfHours());
        constraint.setStartTime(details.getStartTime());
        return constraintRepository.save(constraint);
    }

}
