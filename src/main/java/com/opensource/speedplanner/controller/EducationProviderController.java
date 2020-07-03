package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.EducationProvider;
import com.opensource.speedplanner.resource.EducationProviderResource;
import com.opensource.speedplanner.resource.SaveEducationProviderResource;
import com.opensource.speedplanner.service.EducationProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="education providers", description = "Education providers API")
@RestController
@RequestMapping("/api")
public class EducationProviderController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EducationProviderService educationProviderService;

    @Operation(summary = "Create Education provider", description = "Create an Education provider by given resource",
            tags = {"education providers"})
    @PostMapping("/educationProviders")
    public ResponseEntity<EducationProviderResource> createEducationProvider(@Valid @RequestBody SaveEducationProviderResource resource){
        EducationProvider educationProvider = convertToEntity(resource);
        EducationProviderResource educationProviderResource = convertToResource(educationProviderService.createEducationProvider(educationProvider));
        return new ResponseEntity<EducationProviderResource>(educationProviderResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Education providers", description = "Get All Education providers by Pages",
            tags = {"education providers"})
    @GetMapping("/educationProviders")
    public Page<EducationProviderResource> getAllEducationProviders(Pageable pageable){
        Page<EducationProvider> educationProviders = educationProviderService.getAllEducationProviders(pageable);
        List<EducationProviderResource> resources = educationProviders.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Education provider by Id", description = "Get an Education provider by specifying Id",
            tags = {"education providers"})
    @GetMapping("/educationProviders/{educationProviderId}")
    public ResponseEntity<EducationProviderResource> getEducationProviderById(@PathVariable Long educationProviderId){
        EducationProviderResource educationProviderResource = convertToResource(educationProviderService.getEducationProviderById(educationProviderId));
        return new ResponseEntity<EducationProviderResource>(educationProviderResource, HttpStatus.OK);
    }

    @GetMapping("/profiles/{profileId}/educationProvider")
    public EducationProviderResource getEducationProviderByProfileId(@PathVariable Long profileId){
        return convertToResource(educationProviderService.getEducationProviderByProfileId(profileId));
    }

    @Operation(summary = "Update Education provider", description = "Update an Education provider by specifying Id " +
            "and given resource", tags = {"education providers"})
    @PutMapping("/educationProviders/{educationProviderId}")
    public EducationProviderResource updateEducationProvider(@PathVariable Long educationProviderId,
                                                             @Valid @RequestBody SaveEducationProviderResource resource){
        EducationProvider educationProvider = convertToEntity(resource);
        return convertToResource(educationProviderService.updateEducationProvider(educationProviderId, educationProvider));
    }

    @Operation(summary = "Delete Education provider", description = "Delete an Education provider by specifying Id",
            tags = {"education providers"})
    @DeleteMapping("/educationProviders/{educationProviderId}")
    public ResponseEntity<?> deleteEducationProvider(@PathVariable Long educationProviderId){
        return educationProviderService.deleteEducationProvider(educationProviderId);
    }


    private EducationProvider convertToEntity(SaveEducationProviderResource resource){
        return mapper.map(resource, EducationProvider.class);
    }
    private EducationProviderResource convertToResource(EducationProvider entity){
        return mapper.map(entity, EducationProviderResource.class);
    }
}


