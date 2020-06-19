package com.opensource.speedplanner.controller;

import com.opensource.speedplanner.model.HourRangeConstraint;
import com.opensource.speedplanner.resource.HourRangeConstraintResource;
import com.opensource.speedplanner.resource.SaveHourRangeConstraintResource;
import com.opensource.speedplanner.service.HourRangeConstraintService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HourRangeConstraintController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private HourRangeConstraintService hourRangeConstraintService;


    //NO HAY CONTROLLER, EL SERVICE ES LLAMADO POR INSC

    private HourRangeConstraint convertToEntity(SaveHourRangeConstraintResource resource){
        return mapper.map(resource, HourRangeConstraint.class);
    }
    private HourRangeConstraintResource convertToResource(HourRangeConstraint entity){
        return mapper.map(entity, HourRangeConstraintResource.class);
    }
}
