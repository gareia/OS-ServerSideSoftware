package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Profile;
import com.opensource.speedplanner.repository.ProfileRepository;
import com.opensource.speedplanner.resource.ProfileResource;
import com.opensource.speedplanner.resource.SaveProfileResource;
import com.opensource.speedplanner.service.ProfileService;
import com.opensource.speedplanner.service.ProfileServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "profiles", description = "Profiles API")
@RestController
@RequestMapping("/api")
public class ProfileController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get Profile " , description = "Get all profiles",
               tags = {"profiles"})
    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(Pageable pageable){
        Page<Profile> profiles = profileService.getAllProfiles(pageable);
        List<ProfileResource> resources = profiles.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }


    @Operation(summary = "Create Profile", description = "Create a Profile by User Id and given resource",
            tags = {"profiles"})
    @PostMapping("/users/{userId}/profiles")
    public ProfileResource createProfile(@PathVariable(name = "userId")Long userId ,
                                   @Valid @RequestBody SaveProfileResource resource){
        return convertToResource(profileService.createProfile(userId,convertToEntity(resource)));
    }

    @Operation(summary = "Update Profile", description = "Update a Profile by specifying Id, " +
            "User Id and given resource", tags = {"profiles"})
    @PutMapping("/users/{userId}/profiles/{profileId}")
    public  ProfileResource updateProfile(@PathVariable(name = "userId") Long userId ,
                                          @PathVariable(name = "profileId") Long profileId,
                                          @Valid @RequestBody SaveProfileResource resource){
        return convertToResource(profileService.updateProfile(userId,profileId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Profile", description = "Delete a Profile by specifying Id " +
            "and User Id", tags = {"profiles"})
    @DeleteMapping("/users/{userId}/profiles/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "userId")Long userId ,
                                           @PathVariable(name = "profileId")Long profileId){
        return profileService.deleteProfile(userId,profileId);
    }

    private Profile convertToEntity(SaveProfileResource resource){
        return  mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity){
        return mapper.map(entity, ProfileResource.class);
    }
}
