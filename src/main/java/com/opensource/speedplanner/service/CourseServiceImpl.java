package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.model.InscriptionProcess;
import com.opensource.speedplanner.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EducationProviderRepository educationProviderRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InscriptionProcessRepository inscriptionProcessRepository;


    @Override
    public Course createCourse(Long educationProviderId, Course course) {
        return educationProviderRepository.findById(educationProviderId).map(educationProvider -> {
            course.setEducationProvider(educationProvider);
            return courseRepository.save(course);
        }).orElseThrow(()->new ResourceNotFoundException("EducationProvider", "Id", educationProviderId));
    }

    @Override
    public Course getCourseByIdAndEducationProviderId(Long educationProviderId, Long courseId) {
        return courseRepository.findByIdAndEducationProviderId(courseId, educationProviderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Course with id: %s and Education provider with id: %s not found",
                                courseId, educationProviderId)));
    }

    @Override
    public Page<Course> getAllCoursesByEducationProviderId(Long educationProviderId, Pageable pageable) {
        return courseRepository.findByEducationProviderId(educationProviderId, pageable);

    }

    @Override
    public List<Course> getAllCoursesByProfileId(Long profileId) {
        return profileRepository.findById(profileId).map(profile ->
                profile.getLearningProgram().getCourses()
        ).orElseThrow(()->new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Course getCourseById(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(()->new ResourceNotFoundException("Course", "Id", courseId));
    }

    @Override
    public List<Course> getAllCoursesByUserId(Long userId){
        Long inscriptionProcessId = userRepository.findById(userId).get().getInscriptionProcess().getId();
        return inscriptionProcessRepository.findByIdAndUserId(inscriptionProcessId, userId)
                .get().getCourses();
    }

    @Override
    public Course updateCourse(Long educationProviderId, Long courseId, Course details) {
        //find course
        Course course = getCourseByIdAndEducationProviderId(educationProviderId, courseId);
        //now update
        course.setCode(details.getCode());
        course.setName(details.getName());
        course.setOptional(details.isOptional());
        course.setVirtual(details.isVirtual());
        course.setSemester(details.getSemester());
        course.setCredits(details.getCredits());
        //dont set total number of students 'cause it is a calculation
        return courseRepository.save(course);
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long educationProviderId, Long courseId) {
        //find course
        Course course = getCourseByIdAndEducationProviderId(educationProviderId, courseId);
        //now add
        courseRepository.delete(course);
        return ResponseEntity.ok().build();
    }


}
