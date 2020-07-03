package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.repository.EducationProviderRepository;
import com.opensource.speedplanner.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EducationProviderServiceImpl implements EducationProviderService{

    @Autowired
    private EducationProviderRepository educationProviderRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public EducationProvider createEducationProvider(EducationProvider educationProvider) {
        return educationProviderRepository.save(educationProvider);
    }

    @Override
    public EducationProvider getEducationProviderById(Long educationProviderId) {
        return educationProviderRepository.findById(educationProviderId)
                .orElseThrow(()->new ResourceNotFoundException("EducationProvider", "Id", educationProviderId));
    }

    @Override
    public EducationProvider getEducationProviderByProfileId(Long profileId){
        return profileRepository.findById(profileId).map(profile ->
                profile.getLearningProgram().getEducationProvider()
        ).orElseThrow(()->new ResourceNotFoundException("Profile", "Id", profileId));
    }


    @Override
    public Page<EducationProvider> getAllEducationProviders(Pageable pageable) {
        return educationProviderRepository.findAll(pageable);
    }

    @Override
    public EducationProvider updateEducationProvider(Long educationProviderId, EducationProvider educationProviderDetails) {
        EducationProvider educationProvider = getEducationProviderById(educationProviderId);
        //throw exception if educationProviderId is not found
        educationProvider.setName(educationProviderDetails.getName());
        educationProvider.setNumberOfCareers(educationProviderDetails.getNumberOfCareers());
        return educationProviderRepository.save(educationProvider);
    }

    @Override
    public ResponseEntity<?> deleteEducationProvider(Long educationProviderId) {
        EducationProvider educationProvider = getEducationProviderById(educationProviderId);
        //throw exception if educationProviderId is not found
        educationProviderRepository.delete(educationProvider);
        return ResponseEntity.ok().build();
    }


}
