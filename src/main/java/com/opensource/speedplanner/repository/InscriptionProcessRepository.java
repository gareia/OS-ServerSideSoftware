package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.InscriptionProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscriptionProcessRepository extends JpaRepository<InscriptionProcess, Long> {
    Page<InscriptionProcess> findByUserId(Long userId, Pageable pageable);
    Optional<InscriptionProcess> findByIdAndUserId(Long id, Long userId);
}
