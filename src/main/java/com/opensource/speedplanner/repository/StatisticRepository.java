package com.opensource.speedplanner.repository;
import com.opensource.speedplanner.model.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticRepository extends  JpaRepository <Statistic,Long>{
    Page<Statistic> findByUserId(Long userId, Pageable pageable);
    Optional<Statistic> findByIdAndUserId(Long id, Long userId);
}
