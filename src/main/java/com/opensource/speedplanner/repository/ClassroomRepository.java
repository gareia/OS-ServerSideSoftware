package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Classroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
    Page<Classroom> findByEducationProviderId(Long educationProviderId, Pageable pageable);
    Optional<Classroom> findByIdAndEducationProviderId(Long id, Long educationProviderId);
}
