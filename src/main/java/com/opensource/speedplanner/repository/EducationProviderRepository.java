package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.EducationProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationProviderRepository extends JpaRepository<EducationProvider, Long> {
}
