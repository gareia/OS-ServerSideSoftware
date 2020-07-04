package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Constraint;
import com.opensource.speedplanner.model.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstraintRepository extends JpaRepository<Constraint, Long> {
    Constraint findByCourseId(Long courseId);
}
