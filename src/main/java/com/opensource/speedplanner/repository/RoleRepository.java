package com.opensource.speedplanner.repository;

import com.opensource.speedplanner.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Page<Role> findByUserId(Long userId , Pageable pageable);
    Optional<Role> findByIdAndUserId(Long userId , Long roleId);
}
