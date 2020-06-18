package com.opensource.speedplanner.service;
import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Course;
import com.opensource.speedplanner.model.PossibleSchedule;
import com.opensource.speedplanner.repository.CourseRepository;
import com.opensource.speedplanner.repository.LearningProgramRepository;
import com.opensource.speedplanner.repository.PossibleScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PossibleScheduleServiceImpl implements PossibleScheduleService {

    @Autowired
    private PossibleScheduleRepository possibleScheduleRepository;

    @Autowired
    private LearningProgramRepository learningProgramRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Page<PossibleSchedule> getAllPossibleSchedulesByInscriptionProcessId(Long inscriptionProcessId, Pageable pageable) {
        return possibleScheduleRepository.findAllByInscriptionProcessId(inscriptionProcessId, pageable);
    }

    @Override
    public PossibleSchedule createPossibleSchedule(PossibleSchedule possibleSchedule) {
        return possibleScheduleRepository.save(possibleSchedule);
    }

    @Override
    public PossibleSchedule updatePossibleSchedule(Long possibleScheduleId, PossibleSchedule possibleScheduleDetails) {
        return possibleScheduleRepository.findById(possibleScheduleId).map(possibleSchedule ->{
            possibleSchedule.setCourses(possibleScheduleDetails.getCourses());
            possibleSchedule.setCredits(possibleScheduleDetails.getCredits());
            possibleSchedule.setFinal(possibleScheduleDetails.isFinal());
            possibleSchedule.setInscriptionProcess(possibleScheduleDetails.getInscriptionProcess());
            return possibleScheduleRepository.save(possibleSchedule);
        }).
                orElseThrow(()-> new ResourceNotFoundException("Possible Schedule", "Id", possibleScheduleId));
    }

    @Override
    public ResponseEntity<?> deletePossibleSchedule(Long possibleScheduleId, Long inscriptionProcessId) {
        return possibleScheduleRepository.findByIdAndInscriptionProcessId(possibleScheduleId, inscriptionProcessId).
                map(possibleSchedule -> {
            possibleScheduleRepository.delete(possibleSchedule);
            return ResponseEntity.ok().build();
        }).
                orElseThrow(()-> new ResourceNotFoundException("Possible Schedule", "Id", possibleScheduleId));
    }

    @Override
    public Page<PossibleSchedule> getAllPossibleSchedules(Pageable pageable) {
        return possibleScheduleRepository.findAll(pageable);
    }

    @Override
    public PossibleSchedule getByIdAndInscriptionProcessId(Long possibleScheduleId, Long inscriptionProcessId) {
        return possibleScheduleRepository.findByIdAndInscriptionProcessId(possibleScheduleId, inscriptionProcessId).
                orElseThrow(() -> new ResourceNotFoundException("Possible Schedule not found with Id: "+possibleScheduleId+
                        "and InscriptionProcess Id: "+inscriptionProcessId));
    }

    @Override
    public PossibleSchedule assignPossibleScheduleCourse(Long possibleScheduleId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return possibleScheduleRepository.findById(possibleScheduleId).map(possibleSchedule -> {
            if(!possibleSchedule.getCourses().contains(course)) {
                possibleSchedule.getCourses().add(course);
                return possibleScheduleRepository.save(possibleSchedule);
            }
            return possibleSchedule;
        }).orElseThrow(() -> new ResourceNotFoundException("PossibleSchedule", "Id", possibleScheduleId));
    }

    @Override
    public PossibleSchedule unassignPossibleScheduleCourse(Long possibleScheduleId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
        return possibleScheduleRepository.findById(possibleScheduleId).map(possibleSchedule -> {
            possibleSchedule.getCourses().remove(course);
            return possibleScheduleRepository.save(possibleSchedule);
        }).orElseThrow(() -> new ResourceNotFoundException("PossibleSchedule", "Id", possibleScheduleId));
    }

    @Override
    public Page<PossibleSchedule> getAllPossibleScheduleByCourseId(Long courseId, Pageable pageable) {
        return courseRepository.findById(courseId).map(tag -> {
            List<PossibleSchedule> possibleSchedules = tag.getPossibleSchedules();
            int possibleScheduleCount = possibleSchedules.size();
            return new PageImpl<>(possibleSchedules, pageable, possibleScheduleCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Course", "Id", courseId));
    }
}
