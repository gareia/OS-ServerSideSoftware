package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Period;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    Page<Period> findByEducationProviderId(Long educationProviderId, Pageable pageable);
    Optional<Period> findByIdAndEducationProviderId(Long id, Long educationProviderId);
    Period findByCode(String code);

}
