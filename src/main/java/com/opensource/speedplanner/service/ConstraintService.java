package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Constraint;

public interface ConstraintService {

    Constraint createConstraint(Long userId, Constraint constraint);
    Constraint getConstraintByCourseId(Long courseId);
    Constraint updateConstraint(Long courseId, Constraint details);
}
