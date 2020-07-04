package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Constraint;
import com.opensource.speedplanner.model.Section;
import com.opensource.speedplanner.repository.ConstraintRepository;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.SectionRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ConstraintRepository constraintRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Section createSection(Long courseId, Section section) {
        return courseRepository.findById(courseId).map(course -> {
            section.setCourse(course);
            return sectionRepository.save(section);
        }).orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public Page<Section> getAllSectionsByCourseId(Long courseId, Pageable pageable) {
        return sectionRepository.findByCourseId(courseId, pageable);
    }



    public List<Section> getAllSectionsByCourseIdAndProfessorConstraint(Long userId, Long courseId, Pageable pageable) {


        Constraint constraint = constraintRepository.findByCourseId(courseId);
        List<Section> results = sectionRepository.findAll().stream().filter(section ->
                section.getProfessorName().equals(constraint.getProfessorName())).collect(Collectors.toList());

        userRepository.findById(userId).get().getInscriptionProcess().setSections(results);
        return results;
    }



}
