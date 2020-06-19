package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    Page<Profile> getAllProfiles(Pageable pageable);
    Page<Profile> getAllProfilesByUserId(Long userId , Pageable pageable);
    Profile createProfile(Long userId, Profile profile);
    Profile updateProfile(Long userId , Long profileId, Profile profileDetails);
    ResponseEntity<?> deleteProfile(Long postId, Long profileId);
}