package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.persistence.repository.BusinessActivityRepository;
import com.mcb.datareference_service.service.BusinessActivityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BusinessActivityServiceImpl implements BusinessActivityService {
    private final BusinessActivityRepository activityRepository;

    public BusinessActivityServiceImpl(BusinessActivityRepository businessActivityRepository) {
        activityRepository = businessActivityRepository;
    }

    @Override
    public @NonNull List<BusinessActivity> getAll() {
        return activityRepository.findAll();
    }

    @Override
    public @NonNull Optional<BusinessActivity> getUsingUuid(String uuid) {
        log.info("Find All Business Activities");
        return activityRepository.findByActivityUUID(uuid);
    }

    @Override
    public @NonNull BusinessActivity checkAndGetUsingUuid(String uuid) throws EntityNotFoundException {
        log.info("Get and check business activity using uuid {}", uuid);
        return getUsingUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("No Activity with uuid identifier " + uuid));
    }
}
