package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.model.LearningProgram;
import com.opensource.speedplanner.model.Profile;
import com.opensource.speedplanner.repository.EducationProviderRepository;
import com.opensource.speedplanner.repository.LearningProgramRepository;
import com.opensource.speedplanner.repository.ProfileRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EducationProviderRepository educationProviderRepository;
    @Autowired
    private LearningProgramRepository learningProgramRepository;


    //TODO: Prob just learning program relationship needed
    @Override
    public Profile createProfile(Long educationProviderId, Long learningProgramId, Profile profile) {
        LearningProgram lProgram = learningProgramRepository.findByIdAndEducationProviderId(learningProgramId, educationProviderId)
              .orElseThrow(()-> new ResourceNotFoundException(String.format("Education provider with id: %s and " +
                      "Learning program with id: %s were not found", learningProgramId)));
        profile.setEducationProvider(lProgram.getEducationProvider());
        profile.setLearningProgram(lProgram);
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileById(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "Id", profileId));
    }

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Profile updateProfile(Long profileId, Profile details) {
        //find user
        Profile profile = getProfileById(profileId);
        //if exists update
        profile.setNames(details.getNames());
        profile.setLastNames(details.getLastNames());
        profile.setGender(details.isGender());
        profile.setSemester(details.getSemester());
        profile.setIdNumber(details.getIdNumber());
        //TODO: how to set columns of join with id
        return profileRepository.save(profile);
    }

    @Override
    public ResponseEntity<?> deleteProfile(Long profileId) {
        //find user
        Profile profile = getProfileById(profileId);
        //if exists delete
        profileRepository.delete(profile);
        return ResponseEntity.ok().build();
    }
}
