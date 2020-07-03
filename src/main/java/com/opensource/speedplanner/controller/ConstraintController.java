package com.opensource.speedplanner.controller;
/*
import com.opensource.speedplanner.model.Constraint;
import com.opensource.speedplanner.resource.ConstraintResource;
import com.opensource.speedplanner.resource.SaveConstraintResource;
import com.opensource.speedplanner.service.ConstraintService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ConstraintController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ConstraintService constraintService;

    @PostMapping("/users/{userId}/constraints")
    public ConstraintResource createConstraint(@PathVariable Long userId, @Valid @RequestBody SaveConstraintResource resource){
        Constraint constraint = convertToEntity(resource);
        return convertToResource(constraintService.createConstraint(userId, constraint));
    }

    @PutMapping("/users/{userId}/constraints")
    public ConstraintResource updateConstraint(@PathVariable Long userId, @Valid @RequestBody SaveConstraintResource resource){
        Constraint constraint = convertToEntity(resource);
        return convertToResource(constraintService.updateConstraint(userId, constraint));
    }



    private Constraint convertToEntity(SaveConstraintResource resource){
        return mapper.map(resource, Constraint.class);
    }
    private ConstraintResource convertToResource(Constraint entity){
        return mapper.map(entity, ConstraintResource.class);
    }

}
*/