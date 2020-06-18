package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.LearningProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LearningProgramService {


    LearningProgram createLearningProgram(Long educationProviderId, LearningProgram learningProgram);
    LearningProgram getLearningProgramByIdAndEducationProviderId(Long educationProviderId, Long learningProgramId);

    Page<LearningProgram> getAllLearningProgramsByEducationProvider(Long educationProviderId, Pageable pageable);

    LearningProgram updateLearningProgram(Long educationProviderId, Long learningProgramId, LearningProgram learningProgramDetails);
    ResponseEntity<?> deleteLearningProgram(Long educationProviderId, Long learningProgramId);

    LearningProgram assignLearningProgramCourse(Long learningProgramId, Long courseId);
    LearningProgram unassignLearningProgramCourse(Long learningProgramId, Long courseId);
    Page<LearningProgram> getAllLearningProgramByCourseId(Long courseId, Pageable pageable);



}
