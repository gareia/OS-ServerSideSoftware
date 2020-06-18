package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StatisticService {
    Statistic createStatistic(Statistic statistic);
    Statistic getStatisticById(Long statisticId);
    Statistic updateStatistic (Long statisticId, Statistic statisticDetails);
    ResponseEntity<?> deleteStatistic(Long statisticId);
    Page<Statistic> getAllStatistic(Pageable pageable);
}