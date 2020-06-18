package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.LearningProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LearningProgramRepository extends JpaRepository<LearningProgram, Long> {
    Page<LearningProgram> findByEducationProviderId(Long educationProviderId, Pageable pageable);
    Optional<LearningProgram> findByIdAndEducationProviderId(Long id, Long educationProviderId);
}
