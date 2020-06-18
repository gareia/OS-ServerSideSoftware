package com.opensource.speedplanner.controller;
import com.opensource.speedplanner.model.Subscription;
import com.opensource.speedplanner.resource.*;
import com.opensource.speedplanner.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name="subscriptions", description = "Subscriptions API")
@RestController
@RequestMapping("/api")
public class SubscriptionController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubscriptionService subscriptionService;

    @Operation(summary = "Create Subscription", description = "Create Subscription by given resource", tags = {"subscriptions"})
    @PostMapping("/subscriptions")
    public SubscriptionResource createSubscription (@Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.createSubscription(convertToEntity(resource)));
    }

    @Operation(summary = "Get Subscription by Id", description = "Get Subscription by specifying Id", tags = {"subscriptions"})
    @GetMapping("/subscriptions/{id}")
    public SubscriptionResource getSubscriptionById(@PathVariable(name = "id") Long subscriptionId) {
        return convertToResource(subscriptionService.getSubscriptionById(subscriptionId));
    }

    @Operation(summary = "Update Subscription", description = "Update Subscription by specifying Id and given resource",
            tags = {"subscriptions"})
    @PutMapping("/subscriptions/{id}")
    public SubscriptionResource updateSubscription(@PathVariable(name = "id") Long subscriptionId, @Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.updateSubscription(subscriptionId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete Subscription", description = "Delete Subscription by specifying Id", tags = {"subscriptions"})
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable(name = "id") Long subscriptionId) {
        return subscriptionService.deleteSubscription(subscriptionId);
    }

    @Operation(summary = "Get all Subscriptions", description = "Get All Subscriptions by Pages", tags = {"subscriptions"})
    @GetMapping("/subscriptions")
    public Page<SubscriptionResource> getAllSubscription(Pageable pageable) {
        List<SubscriptionResource> subscriptions = subscriptionService.getAllSubscription(pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int subscriptionsCount = subscriptions.size();
        return new PageImpl<>(subscriptions, pageable, subscriptionsCount);
    }

    private Subscription convertToEntity(SaveSubscriptionResource resource) { return mapper.map(resource, Subscription.class); }

    private SubscriptionResource convertToResource(Subscription entity) { return mapper.map(entity, SubscriptionResource.class); }
}
