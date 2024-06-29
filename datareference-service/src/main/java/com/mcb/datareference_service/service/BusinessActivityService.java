package com.mcb.datareference_service.service;

import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BusinessActivityService {

    List<BusinessActivity> getAll();

    Optional<BusinessActivity> getUsingUuid(String uuid);

    BusinessActivity checkAndGetUsingUuid(String uuid) throws EntityNotFoundException;
}
