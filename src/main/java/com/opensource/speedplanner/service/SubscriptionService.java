package com.opensource.speedplanner.service;

import com.opensource.speedplanner.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    Subscription createSubscription (Subscription subscription);
    Subscription getSubscriptionById (Long subscriptionId);
    Subscription updateSubscription (Long subscriptionId, Subscription subscriptionDetails);
    ResponseEntity<?> deleteSubscription (Long subscriptionId);
    Page<Subscription> getAllSubscription(Pageable pageable);
}
