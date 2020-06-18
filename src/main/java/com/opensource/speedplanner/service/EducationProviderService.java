package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.EducationProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EducationProviderService {

    EducationProvider createEducationProvider(EducationProvider educationProvider);
    EducationProvider getEducationProviderById(Long educationProviderId);

    Page<EducationProvider> getAllEducationProviders(Pageable pageable);
    EducationProvider updateEducationProvider(Long educationProviderId, EducationProvider educationProviderDetails);
    ResponseEntity<?> deleteEducationProvider(Long educationProviderId);

}
