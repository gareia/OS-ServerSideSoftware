package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Period;
import com.opensource.speedplanner.resource.PeriodResource;
import com.opensource.speedplanner.resource.SavePeriodResource;
import com.opensource.speedplanner.service.PeriodService;
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

@Tag(name="periods", description = "Periods API")
@RestController
@RequestMapping(value = "/api")
public class PeriodController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PeriodService periodService;

    private Period convertToEntity(SavePeriodResource periodResource){
        return mapper.map(periodResource, Period.class);
    }

    private PeriodResource convertToResource(Period entity){
        return mapper.map(entity, PeriodResource.class);
    }


    @Operation(summary = "Create Period", description = "Creates a new period for a Learning Program, given" +
            "its Id.", tags = { "periods" })
    @PostMapping("/learningPrograms/{learningProgramId}/periods")
    public PeriodResource createPeriod(@PathVariable(name = "learningProgramId") Long learningProgramId,
                                       @Valid @RequestBody SavePeriodResource resource){
        return convertToResource(periodService.createPeriod(learningProgramId, convertToEntity(resource)));
    }


    @Operation(summary = "Get All Periods by Learning Program Id", description = "Gets all the periods from" +
            "a Learning Program, specifying its Id.", tags = { "periods" })
    @GetMapping("/learningPrograms/{learningProgramId}/periods")
    public Page<PeriodResource> getAllPeriodsByLearningProgramId(@PathVariable(name="learningProgramId")
            Long learningProgramId, Pageable pageable){
        Page<Period> periodPage = periodService.getAllPeriodsByLearningProgramId(learningProgramId, pageable);
        List<PeriodResource> resources = periodPage.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get Period by Id and Learning Program Id", description = "Gets and individual" +
            "period, given its Id and its corresponding Learning Program Id.", tags = { "periods" })
    @GetMapping("/learningPrograms/{learningProgramId}/periods/{periodId}")
    public PeriodResource getPeriodByIdAndLearningProgramId(@PathVariable(name="learningProgramId")
                            Long learningProgramId, @PathVariable(name = "periodId") Long periodId){
        return convertToResource(periodService.getByIdAndLearningProgramId(periodId, learningProgramId));
    }

    @Operation(summary = "Update Period", description = "Updates a period's attributes, given its" +
            "Id and the corresponding Learning Program Id.", tags = { "periods" })
    @PutMapping("/learningPrograms/{learningProgramId}/periods/{periodId}")
    public PeriodResource updatePeriod(@PathVariable(name="learningProgramId") Long learningProgramId,
                                         @PathVariable(name = "periodId") Long periodId,
                                         @Valid @RequestBody SavePeriodResource periodResource){
        return convertToResource(periodService.updatePeriod(periodId, learningProgramId, convertToEntity(periodResource)));
    }

    @Operation(summary = "Delete Period", description = "Deletes a period, given its Id and the corresponding" +
            "Learning program Id.", tags = { "periods" })
    @DeleteMapping("/learningPrograms/{learningProgramId}/periods/{periodId}")
    public ResponseEntity<?> deletePeriod(@PathVariable(name = "periodId") Long periodId,
                                          @PathVariable(name= "learningProgramId") Long learningProgramId){
        return periodService.deletePeriod(periodId, learningProgramId);
    }


}