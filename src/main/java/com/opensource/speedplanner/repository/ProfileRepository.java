package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile , Long> {
    Page<Profile> findByUserId(Long userId , Pageable pageable);
    Optional<Profile> findByIdAndUserId(Long userId , Long profileId);
}