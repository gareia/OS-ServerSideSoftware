package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByInscriptionProcessId(Long inscriptionProcessId, Pageable pageable);
    Optional<Course> findByIdAndInscriptionProcessId(Long id, Long inscriptionProcessId);
}
