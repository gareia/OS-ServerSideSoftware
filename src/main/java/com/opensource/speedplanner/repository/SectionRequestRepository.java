package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.SectionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRequestRepository extends JpaRepository<SectionRequest , Long> {
    Page<SectionRequest> findByInscriptionProcessId (Long inscriptionProcessId, Pageable pageable);
    Optional<SectionRequest>  findByIdAndInscriptionProcessId(Long inscriptionProcessId, Long sectionRequestId);
}
