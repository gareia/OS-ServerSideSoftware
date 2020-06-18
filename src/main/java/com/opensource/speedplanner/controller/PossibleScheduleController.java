package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Classroom;
import com.opensource.speedplanner.model.PossibleSchedule;
import com.opensource.speedplanner.resource.ClassroomResource;
import com.opensource.speedplanner.resource.PossibleScheduleResource;
import com.opensource.speedplanner.resource.SavePossibleScheduleResource;
import com.opensource.speedplanner.service.PossibleScheduleService;
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

@Tag(name="possible schedules", description = "Possible Schedules API")
@RestController
@RequestMapping(value = "/api")
public class PossibleScheduleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PossibleScheduleService possibleScheduleService;

    private PossibleScheduleResource convertToResource(PossibleSchedule entity){
        return mapper.map(entity, PossibleScheduleResource.class);
    }

    private PossibleSchedule convertToEntity(SavePossibleScheduleResource resource){
        return mapper.map(resource, PossibleSchedule.class);
    }

    @Operation(summary = "Get All Possible Schedules by InscriptionProcessId", description = "Gets all " +
            "the Possible Schedules corresponding to an Inscription Process, given its Id.", tags = { "possible " +
            "schedules" })
    @GetMapping("/inscriptionProcesses/{inscriptionProcessId}/possibleSchedules")
    public Page<PossibleScheduleResource> getAllPossibleSchedulesByInscriptionProcessId(
            @PathVariable(name = "inscriptionProcessId") Long inscriptionProcessId, Pageable pageable){
            Page<PossibleSchedule> possibleSchedulePage = possibleScheduleService.
                    getAllPossibleSchedulesByInscriptionProcessId(inscriptionProcessId, pageable);
        List<PossibleScheduleResource> resources =possibleSchedulePage.getContent().stream().
                map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
        }
    @Operation(summary = "Get By Id and Inscription Process Id", description = "Gets a particular Possible Schedule, "+
            "given its Id and the corresponding Inscription Process Id.", tags = { "possible schedules" })
    @GetMapping("/inscriptionProcesses/{inscriptionProcessId}/possibleSchedules/{possibleScheduleId}")
    public PossibleScheduleResource getByIdAndInscriptionProcessId(@PathVariable(name = "inscriptionProcessId")
                                                                               Long inscriptionProcessId,
                                                                   @PathVariable(name = "possibleScheduleId")
                                                                           Long possibleScheduleId){
        return convertToResource(possibleScheduleService.getByIdAndInscriptionProcessId(possibleScheduleId,
                inscriptionProcessId));
    }
    @Operation(summary = "Create Possible Schedule", description = "Creates a new Possible Schedule, specifying " +
            "the Id of the Inscription Process related to it.", tags = { "possible schedules" })
    @PostMapping("/inscriptionProcesses/{inscriptionProcessId}/possibleSchedules/")
    public PossibleScheduleResource createPossibleSchedule(@PathVariable(name = "inscriptionProcessId")
                                                                       Long inscriptionProcessId,
                                                           @Valid @RequestBody SavePossibleScheduleResource resource){
        return convertToResource(possibleScheduleService.createPossibleSchedule(convertToEntity(resource)));
    }

    @Operation(summary = "Update Possible Schedule", description = "Updates the attributes of a particular " +
            "Possible Schedule, given its Id, and the Id of the corresponding Inscription Process.",
            tags = { "possible" + " schedules" })
    @PutMapping("/inscriptionProcesses/{inscriptionProcessId}/possibleSchedules/{possibleScheduleId}")
    public PossibleScheduleResource updatePossibleSchedule(@PathVariable(name = "inscriptionProcessId")
                                                                       Long inscriptionProcessId,
                                                           @PathVariable(name = "possibleScheduleId")
                                                                   Long possibleScheduleId,
                                                           @Valid @RequestBody SavePossibleScheduleResource resource){
        return convertToResource(possibleScheduleService.updatePossibleSchedule(possibleScheduleId,
                convertToEntity(resource)));
    }

    @Operation(summary = "Delete Possible Schedule", description = "Deletes a particular Possible Schedule, given " +
            "its Id, and the Id of the corresponding Inscription Process", tags = { "possible schedules" })
    @DeleteMapping("/inscriptionProcesses/{inscriptionProcessId}/possibleSchedules/{possibleScheduleId}")
    public ResponseEntity<?> deletePossibleSchedule(@PathVariable(name = "inscriptionProcessId")
                                                                Long inscriptionProcessId,
                                                    @PathVariable(name = "possibleScheduleId")
                                                            Long possibleScheduleId){
        return possibleScheduleService.deletePossibleSchedule(possibleScheduleId, inscriptionProcessId);
    }

    @PostMapping("/possibleSchedules/{possibleScheduleId}/courses/{courseId}")
    public PossibleScheduleResource assignPossibleScheduleCourse(@PathVariable(name = "possibleScheduleId") Long possibleScheduleId,
                                                   @PathVariable(name = "courseId") Long courseId) {
        return convertToResource(possibleScheduleService.assignPossibleScheduleCourse(possibleScheduleId, courseId));
    }
    @DeleteMapping("/possibleSchedules/{possibleScheduleId}/course/{courseId}")
    public PossibleScheduleResource unassignPossibleScheduleCourse(@PathVariable(name = "possibleScheduleId") Long possibleScheduleId,
                                                                 @PathVariable(name = "courseId") Long courseId) {
        return convertToResource(possibleScheduleService.assignPossibleScheduleCourse(possibleScheduleId, courseId));
    }
    @GetMapping("/courses/{courseId}/possibleSchedules")
    public Page<PossibleScheduleResource> getAllPossibleScheduleByCourseId(@PathVariable(name = "courseId") Long courseId, Pageable pageable) {
        Page<PossibleSchedule> possibleSchedulePage = possibleScheduleService.getAllPossibleScheduleByCourseId(courseId, pageable);
        List<PossibleScheduleResource> resources = possibleSchedulePage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
}
