package com.opensource.speedplanner.controller;

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

    @PostMapping("/users/courses/{courseId}/constraint")
    public ConstraintResource createConstraint(@PathVariable Long courseId, @Valid @RequestBody SaveConstraintResource resource){
        Constraint constraint = convertToEntity(resource);
        return convertToResource(constraintService.createConstraint(courseId, constraint));
    }

    @GetMapping("/users/courses/{courseId}/constraint")
    public ConstraintResource getConstraintByCourseId(@PathVariable Long courseId){
        return convertToResource(constraintService.getConstraintByCourseId(courseId));
    }

    @PutMapping("/users/courses/{courseId}/constraint")
    public ConstraintResource updateConstraint(@PathVariable Long courseId, @Valid @RequestBody SaveConstraintResource resource){
        Constraint constraint = convertToEntity(resource);
        return convertToResource(constraintService.updateConstraint(courseId, constraint));
    }



    private Constraint convertToEntity(SaveConstraintResource resource){
        return mapper.map(resource, Constraint.class);
    }
    private ConstraintResource convertToResource(Constraint entity){
        return mapper.map(entity, ConstraintResource.class);
    }

}
