package com.opensource.speedplanner.service;


import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.LearningProgram;
import com.opensource.speedplanner.repository.EducationProviderRepository;
import com.opensource.speedplanner.repository.LearningProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LearningProgramServiceImpl implements LearningProgramService{

    @Autowired
    private LearningProgramRepository learningProgramRepository;


    @Autowired
    private EducationProviderRepository educationProviderRepository;

    @Override
    public LearningProgram createLearningProgram(Long educationProviderId, LearningProgram learningProgram) {
        return educationProviderRepository.findById(educationProviderId).map(educationProvider -> {
                    learningProgram.setEducationProvider(educationProvider);
                    return learningProgramRepository.save(learningProgram);
                }).orElseThrow(()->new ResourceNotFoundException("EducationProvider", "Id", educationProviderId));
    }

    @Override
    public LearningProgram getLearningProgramByIdAndEducationProviderId(Long educationProviderId, Long learningProgramId) {
        return learningProgramRepository.findByIdAndEducationProviderId(learningProgramId, educationProviderId)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("LearningProgram with Id: %s and EducationProvider with Id: %s not found",
                                learningProgramId, educationProviderId)
                ));
    }



    @Override
    public Page<LearningProgram> getAllLearningProgramsByEducationProvider(Long educationProviderId, Pageable pageable) {
        return learningProgramRepository.findByEducationProviderId(educationProviderId, pageable);
    }


    @Override
    public LearningProgram updateLearningProgram(Long educationProviderId, Long learningProgramId, LearningProgram learningProgramDetails) {
        LearningProgram learningProgram = getLearningProgramByIdAndEducationProviderId(educationProviderId, learningProgramId);
        //throws an exception if learningProgramId or educationProviderId arent found
        learningProgram.setType(learningProgramDetails.getType());
        learningProgram.setName(learningProgramDetails.getName());
        learningProgram.setNumberOfCourses(learningProgramDetails.getNumberOfCourses());
        return learningProgramRepository.save(learningProgram);
    }

    @Override
    public ResponseEntity<?> deleteLearningProgram(Long educationProviderId, Long learningProgramId) {
        LearningProgram learningProgram = getLearningProgramByIdAndEducationProviderId(educationProviderId, learningProgramId);
        //throws an exception if learningProgramId or educationProviderId arent found
        learningProgramRepository.delete(learningProgram);
        return ResponseEntity.ok().build();
    }


}
