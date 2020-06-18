package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.PossibleSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PossibleScheduleRepository extends JpaRepository<PossibleSchedule, Long> {
    Page<PossibleSchedule> findAllByInscriptionProcessId(Long inscriptionProcessId, Pageable pageable);
    Optional<PossibleSchedule> findByIdAndInscriptionProcessId(Long possibleScheduleId, Long inscriptionProcessId);
}
