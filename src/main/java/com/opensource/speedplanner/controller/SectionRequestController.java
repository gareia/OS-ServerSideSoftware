package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.SectionRequest;
import com.opensource.speedplanner.resource.SaveSectionRequestResource;
import com.opensource.speedplanner.resource.SectionRequestResource;
import com.opensource.speedplanner.service.SectionRequestService;
import com.opensource.speedplanner.service.SectionRequestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "section requests", description = "Section requests API")
@RestController
@RequestMapping("/api")
public class SectionRequestController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SectionRequestService sectionRequestService;


    @Operation(summary = "Create Section request", description = "Create a Section request by Inscription process Id and " +
            "given resource", tags = {"section requests"})
    @PostMapping("/inscription_processes/{inscriptionProcessId}/section_requests")
    public SectionRequestResource createSectionRequest(@PathVariable(name = "inscriptionProcessId")Long inscriptionProcessId,
                                                       @Valid @RequestBody SaveSectionRequestResource resource){
        return  convertToResource(sectionRequestService.createSectionRequest(inscriptionProcessId , convertToEntity(resource)));
    }

    @Operation(summary = "Update Section request", description = "Update a Section request by specifying Id, " +
            "Inscription process Id and given resource", tags = {"section requests"})
    @PutMapping("/inscription_processes/{inscriptionProcessId}/section_requests/{sectionRequestId}")
    public SectionRequestResource updateSectionRequest(@PathVariable(name = "inscriptionProcessId")Long inscriptionProcessId,
                                                       @PathVariable(name = "sectionRequestId")Long sectionRequestId,
                                                       @Valid @RequestBody SaveSectionRequestResource resource){
        return  convertToResource(sectionRequestService.updateSectionRequest(inscriptionProcessId, sectionRequestId , convertToEntity(resource)));
    }

    @Operation(summary = "Delete Section request", description = "Delete a Section request by specifying Id " +
            "and Inscription process Id", tags = {"section requests"})
    @DeleteMapping("/inscription_processes/{inscriptionProcessId}/section_requests/{sectionRequestId}")
    public ResponseEntity<?> deleteSectionRequest(@PathVariable(name = "inscriptionProcessId") Long inscriptionProcessId,
                                                  @PathVariable(name = "sectionRequestId") Long sectionRequestId){
        return  sectionRequestService.deleteSectionRequest(inscriptionProcessId, sectionRequestId);
    }

    private  SectionRequest convertToEntity(SaveSectionRequestResource resource){
        return  mapper.map(resource, SectionRequest.class);
    }

    private SectionRequestResource convertToResource(SectionRequest entity){
        return mapper.map(entity, SectionRequestResource.class);
    }
}
