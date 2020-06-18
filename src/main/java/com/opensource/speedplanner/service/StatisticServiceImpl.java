package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Statistic;
import com.opensource.speedplanner.repository.StatisticRepository;
import com.opensource.speedplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService{
    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Statistic createStatistic(Long userId, Statistic statistic)
    {
        return userRepository.findById(userId).map(user -> {
            statistic.setUser(user);
            return statisticRepository.save(statistic);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User", "Id", userId));
    }

    @Override
    public Statistic getStatisticById(Long statisticId)
    {
        return statisticRepository.findById(statisticId)
                .orElseThrow(() -> new ResourceNotFoundException("Statistic", "Id", statisticId));
    }

    @Override
    public Statistic getStatisticByIdAndUserId(Long userId, Long statisticId) {
        return statisticRepository.findByIdAndUserId(statisticId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Statistic not found with Id " + statisticId +
                                " and UserId " + userId));
    }

    @Override
    public Statistic updateStatistic (Long userId, Long statisticId, Statistic statisticDetails) {
        if(!userRepository.existsById(userId))
            throw new ResourceNotFoundException("User", "Id", userId);

        return statisticRepository.findById(statisticId).map(statistic-> {
            statistic.setMenPercentageInPeriod(statisticDetails.getMenPercentageInPeriod());
            statistic.setWomenPercentageInPeriod(statisticDetails.getWomenPercentageInPeriod());
            statistic.setRegisteredStudentsInPeriod(statisticDetails.getRegisteredStudentsInPeriod());
            return statisticRepository.save(statistic);
        }).orElseThrow(() -> new ResourceNotFoundException("Statistic", "Id", statisticId));
    }

    @Override
    public ResponseEntity<?> deleteStatistic(Long userId, Long statisticId) {
        return statisticRepository.findByIdAndUserId(statisticId, userId).map(statistic -> {
            statisticRepository.delete(statistic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Statistic not found with Id " + statisticId + " and UserId " + userId));
    }

    @Override
    public Page<Statistic> getAllStatistic(Pageable pageable) {
        return statisticRepository.findAll(pageable);
    }
}



