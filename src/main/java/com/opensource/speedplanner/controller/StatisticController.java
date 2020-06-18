package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.Statistic;
import com.opensource.speedplanner.resource.SaveStatisticResource;
import com.opensource.speedplanner.resource.StatisticResource;
import com.opensource.speedplanner.resource.UserResource;
import com.opensource.speedplanner.service.StatisticService;
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

@Tag(name="statistics", description = "Statistics API")
@RestController
@RequestMapping("/api")
public class StatisticController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StatisticService statisticService;

    @Operation(summary = "Create Statistic", description = "Create a Statistic by User Id and given resource",
            tags = {"statistics"})
    @PostMapping("/users/{userId}/statistics")
    public StatisticResource createStatistic(@PathVariable(name = "userId") Long userId,
                                             @Valid @RequestBody SaveStatisticResource resource) {
        return convertToResource(statisticService.createStatistic(userId, convertToEntity(resource)));
    }

    @Operation(summary = "Get Statistic by Id", description = "Get Statistic by specifying Id", tags = {"statistics"})
    @GetMapping("/statistics/{id}")
    public StatisticResource getStatisticById(@PathVariable(name = "id") Long statisticId) {
        return convertToResource(statisticService.getStatisticById(statisticId));
    }

    @Operation(summary = "Get Statistic by Id and User Id", description = "Get Statistic by specifying Id and specifying User Id", tags ={"statistics"})
    @GetMapping("/users/{userId}/statistics/{statisticId}")
    public StatisticResource getStatisticByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "statisticId") Long statisticId) {
        return convertToResource(statisticService.getStatisticByIdAndUserId(userId, statisticId));
    }

    @Operation(summary = "Update Statistic", description = "Update Statistic by specifying Id " + " user Id and given resource",
            tags = {"statistics"})
    @PutMapping("/users/{userId}/statistics/{statisticId}")
    public StatisticResource updateStatistic(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "statisticId") Long statisticId,
                                             @Valid @RequestBody SaveStatisticResource resource) {
        return convertToResource(statisticService.updateStatistic(userId, statisticId,  convertToEntity(resource)));
    }

    @Operation(summary = "Delete Statistic", description = "Delete Statistic by specifying Id" + "and User Id", tags = {"statistics"})
    @DeleteMapping("/users/{userId}/statistics/{statisticId}")
    public ResponseEntity<?> deleteStatistic(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "statisticId") Long statisticId) {
        return statisticService.deleteStatistic(userId, statisticId);
    }

    @Operation(summary = "Get all Statistics", description = "Get All Statistics by Pages", tags = {"statistics"})
    @GetMapping("/statistics")
    public Page<StatisticResource> getAllStatistic(Pageable pageable) {
        List<StatisticResource> statistic = statisticService.getAllStatistic(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int statisticsCount = statistic.size();
        return new PageImpl<>(statistic, pageable, statisticsCount);
    }

    private Statistic convertToEntity(SaveStatisticResource resource) { return mapper.map(resource, Statistic.class); }

    private StatisticResource convertToResource(Statistic entity) { return mapper.map(entity, StatisticResource.class); }
}
