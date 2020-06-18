package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Profile;
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
    private UserRepository userRepository;

    @Override
    public Page<Profile> getAllProfiles(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    public Page<Profile> getAllProfilesByUserId(Long userId, Pageable pageable) {
        return  profileRepository.findByUserId(userId , pageable);
    }

    @Override
    public Profile createProfile(Long userId, Profile profile) {
        return userRepository.findById(userId).map(user -> {
            return  profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId", "Id" , "userId"));
    }
    @Override
    public Profile updateProfile(Long userId, Long profileId, Profile profileDetails) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", "userId");
        return profileRepository.findById(profileId).map(profile -> {
            profile.setLastNames(profileDetails.getLastNames());
            profile.setName(profileDetails.getName());
            return profileRepository.save(profile);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId", "Id" , "userId"));
    }
    @Override
    public ResponseEntity<?> deleteProfile(Long userId, Long profileId) {
        return profileRepository.findByIdAndUserId(userId , profileId).map(profile -> {
            profileRepository.delete(profile);
            return  ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Profile not found with Id" + profileId + " and userId" + userId));
    }
}
