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
@RequestMapping("/api")
public class PeriodController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PeriodService periodService;


    @PostMapping("/educationProviders/{educationProviderId}/periods")
    public PeriodResource createPeriod(@PathVariable Long educationProviderId,
                                       @Valid @RequestBody SavePeriodResource resource){
        return convertToResource(periodService.createPeriod(educationProviderId, convertToEntity(resource)));
    }

    @GetMapping("/educationProviders/{educationProviderId}/periods")
    public Page<PeriodResource> getAllPeriodsByEducationProviderId(@PathVariable Long educationProviderId,
                                                                 Pageable pageable){
        Page<Period> periods = periodService.getAllPeriodsByEducationProviderId(educationProviderId, pageable);
        List<PeriodResource> resources = periods.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/educationProviders/{educationProviderId}/periods/{periodId}")
    public PeriodResource getByIdAndEducationProviderId(@PathVariable Long educationProviderId, @PathVariable Long periodId){
        return convertToResource(periodService.getByIdAndEducationProviderId(educationProviderId, periodId));
    }

/*
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
*/

    private Period convertToEntity(SavePeriodResource resource){
        return mapper.map(resource, Period.class);
    }

    private PeriodResource convertToResource(Period entity){
        return mapper.map(entity, PeriodResource.class);
    }
}

