package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Profile;
import com.opensource.speedplanner.resource.ProfileResource;
import com.opensource.speedplanner.resource.SaveProfileResource;
import com.opensource.speedplanner.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProfileService profileService;

    @PostMapping("/profiles")
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource resource){
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.createProfile(resource.getEducation_provider_id(),
                resource.getLearning_program_id(), profile));
    }

    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable){
        Page<Profile> profiles = profileService.getAllProfiles(pageable);
        List<ProfileResource> resources = profiles.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/profiles/{profileId}") //get profile by user id
    public ProfileResource getProfileById(@PathVariable Long profileId){
        return convertToResource(profileService.getProfileById(profileId));
    }

    @PutMapping("/profiles/{profileId}")
    public ProfileResource updateProfile(@PathVariable Long profileId, @Valid @RequestBody SaveProfileResource resource){
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.updateProfile(profileId, profile));
    }

    @DeleteMapping("/profiles/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long profileId){
        return profileService.deleteProfile(profileId);
    }


    private Profile convertToEntity(SaveProfileResource resource){
        return mapper.map(resource, Profile.class);
    }
    private ProfileResource convertToResource(Profile entity){
        return mapper.map(entity, ProfileResource.class);
    }


}
