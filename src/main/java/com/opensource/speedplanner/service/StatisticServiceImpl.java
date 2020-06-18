package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Statistic;
import com.opensource.speedplanner.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService{
    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public Statistic createStatistic(Statistic statistic) { return statisticRepository.save(statistic); }

    @Override
    public Statistic getStatisticById(Long statisticId) {
        return statisticRepository.findById(statisticId)
                .orElseThrow(() -> new ResourceNotFoundException("Statistic", "Id", statisticId));
    }

    @Override
    public Statistic updateStatistic (Long statisticId, Statistic statisticDetails) {
        return statisticRepository.findById(statisticId).map(statistic-> {
            statistic.setMenPercentageInPeriod(statisticDetails.getMenPercentageInPeriod());
            statistic.setWomenPercentageInPeriod(statisticDetails.getWomenPercentageInPeriod());
            statistic.setRegisteredStudentsInPeriod(statisticDetails.getRegisteredStudentsInPeriod());
            return statisticRepository.save(statistic);
        }).orElseThrow(() -> new ResourceNotFoundException("Statistic", "Id", statisticId));
    }

    @Override
    public ResponseEntity<?> deleteStatistic(Long statisticId) {
        Statistic statistic = statisticRepository.findById(statisticId)
                .orElseThrow(() -> new ResourceNotFoundException("Statistic", "Id", statisticId));
        statisticRepository.delete(statistic);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Statistic> getAllStatistic(Pageable pageable) {
        return statisticRepository.findAll(pageable);
    }
}



