package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.model.Section;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private CourseRepository courseRepository;


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
}
