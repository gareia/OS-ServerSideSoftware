package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Constraint;

public interface ConstraintService {

    Constraint createConstraint(Long userId, Constraint constraint);
    Constraint getConstraintByUserId(Long userId);
    Constraint updateConstraint(Long userId, Constraint details);
}
