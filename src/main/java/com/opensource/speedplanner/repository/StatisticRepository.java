package com.opensource.speedplanner.repository;
import com.opensource.speedplanner.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends  JpaRepository <Statistic,Long>{
}
