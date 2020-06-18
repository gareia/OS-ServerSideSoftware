package com.opensource.speedplanner.service;

import com.opensource.speedplanner.exception.ResourceNotFoundException;
import com.opensource.speedplanner.model.Subscription;
import com.opensource.speedplanner.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {return subscriptionRepository.save(subscription);}

    @Override
    public Subscription getSubscriptionById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "Id", subscriptionId));
    }

    @Override
    public Subscription updateSubscription(Long subscriptionId, Subscription subscriptionDetails) {
        return subscriptionRepository.findById(subscriptionId).map(subscription-> {
            subscription.setCost(subscriptionDetails.getCost());
            subscription.setStartDate(subscriptionDetails.getStartDate());
            subscription.setEndDate(subscriptionDetails.getEndDate());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new ResourceNotFoundException("Subscription", "Id", subscriptionId));
    }

    @Override
    public ResponseEntity<?> deleteSubscription(Long subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "Id", subscriptionId));
        subscriptionRepository.delete(subscription);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Subscription> getAllSubscription(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }
}
