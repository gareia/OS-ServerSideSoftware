package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.InscriptionProcess;
import com.opensource.speedplanner.resource.InscriptionProcessResource;
import com.opensource.speedplanner.resource.SaveInscriptionProcessResource;
import com.opensource.speedplanner.service.InscriptionProcessService;
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

@Tag(name="inscription processes", description = "Inscription processes API")
@RestController
@RequestMapping("/api/users/{userId}/inscriptionProcesses")
public class InscriptionProcessController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private InscriptionProcessService inscriptionProcessService;

    @Operation(summary = "Create Inscription process", description = "Create an Inscription process by User Id and given resource",
            tags = {"inscription processes"})
    @PostMapping
    public InscriptionProcessResource createInscriptionProcess(@PathVariable Long userId,
                                                               @Valid @RequestBody SaveInscriptionProcessResource resource){
        InscriptionProcess inscriptionProcess = convertToEntity(resource);
        return convertToResource(inscriptionProcessService.createInscriptionProcess(userId, inscriptionProcess));
    }
    @Operation(summary = "Get Inscription processes by User Id", description = "Get All Inscription processes by Pages " +
            "and User Id", tags = {"inscription processes"})
    @GetMapping
    public Page<InscriptionProcessResource> getAllInscriptionProcessesByUserId(@PathVariable Long userId, Pageable pageable){
        Page<InscriptionProcess> inscriptionProcesses = inscriptionProcessService.getAllInscriptionProcessesByUserId(userId, pageable);
        List<InscriptionProcessResource> resources = inscriptionProcesses.getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Inscription process by Id and User Id", description = "Get an Inscription process by " +
            "specifying Id and User Id", tags = {"inscription processes"})
    @GetMapping("/{inscriptionProcessId}")
    public InscriptionProcessResource getInscriptionProcessByIdAndUserId(@PathVariable Long userId, @PathVariable Long inscriptionProcessId){
        return convertToResource(inscriptionProcessService.getInscriptionProcessByIdAndUserId(userId, inscriptionProcessId));
    }
    /*
    @PutMapping("/inscriptionProcesses/{inscriptionProcessId}")
    public InscriptionProcessResource updateInscriptionProcess(@PathVariable Long inscriptionProcessId,
                                                             @Valid @RequestBody SaveInscriptionProcessResource resource){
        InscriptionProcess inscriptionProcess = convertToEntity(resource);
        return convertToResource(inscriptionProcessService.updateInscriptionProcess(inscriptionProcessId, inscriptionProcess));
    }*/
    @Operation(summary = "Delete Inscription process", description = "Delete an Inscription process by specifying Id and " +
            "User Id", tags = {"inscription processes"})
    @DeleteMapping("/{inscriptionProcessId}")
    public ResponseEntity<?> deleteInscriptionProcess(@PathVariable Long userId, @PathVariable Long inscriptionProcessId){
        return inscriptionProcessService.deleteInscriptionProcess(userId, inscriptionProcessId);
    }


    private InscriptionProcess convertToEntity(SaveInscriptionProcessResource resource){
        return mapper.map(resource, InscriptionProcess.class);
    }
    private InscriptionProcessResource convertToResource(InscriptionProcess entity){
        return mapper.map(entity, InscriptionProcessResource.class);
    }
}
