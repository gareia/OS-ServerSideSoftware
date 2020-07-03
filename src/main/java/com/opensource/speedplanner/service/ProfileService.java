package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Profile createProfile(Long educationProviderId, Long learningProgramId, Profile profile);
    Profile getProfileById(Long profileId);

    Page<Profile> getAllProfiles(Pageable pageable);
    Profile updateProfile (Long profileId, Profile details);
    ResponseEntity<?> deleteProfile (Long profileId);
}
