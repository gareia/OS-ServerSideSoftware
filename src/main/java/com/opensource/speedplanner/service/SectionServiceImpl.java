package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Section;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<Section> getAllSections(Pageable pageable) {
        return sectionRepository.findAll(pageable);
    }

    @Override
    public Page<Section> getAllSectionsByCourseId(Long courseId, Pageable pageable) {
        return sectionRepository.findAll( pageable);
    }

    @Override
    public Section getSectionByIdAndCourseId(Long courseId, Long sectionId) {
        return sectionRepository.findByIdAndCourseId(courseId , sectionId).
                orElseThrow(() -> new ResourceNotFoundException("Section not found with Id" + sectionId + "and courseId" +  courseId));
    }

    @Override
    public Section createSection(Long courseId, Section section) {
        return courseRepository.findById(courseId).map(course -> {
            return sectionRepository.save(section);
        }).orElseThrow(() -> new ResourceNotFoundException("CourseId" , "Id " , "courseId"));
    }

    @Override
    public Section updateSection(Long courseId, Long sectionId, Section sectionDetails) {
        if(courseRepository.existsById(courseId))
            throw  new ResourceNotFoundException("CourseId" , "Id " , "courseId");
        return  sectionRepository.findById(sectionId).map(section -> {
            section.setVenue(sectionDetails.getVenue());
            section.setRegisteredStudents(sectionDetails.getRegisteredStudents());
            section.setVacancy(sectionDetails.getVacancy());
            return sectionRepository.save(section);
            }).orElseThrow(() -> new ResourceNotFoundException("Section" , "Id" , "sectionId"));
    }

    @Override
    public ResponseEntity<?> deleteSection(Long courseId, Long sectionId) {
        return sectionRepository.findByIdAndCourseId(courseId, sectionId).map(section -> {
            sectionRepository.delete(section);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Section not found Id" + sectionId + "and CourseId "  + courseId
        ));
    }

}
