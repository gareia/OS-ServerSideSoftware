package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Page<User> findByProfileId(Long profileId, Pageable pageable);
    Optional<User> findByIdAndProfileId(Long id, Long profileId);
}
