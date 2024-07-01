package com.mcb.datareference_service.service;

import com.mcb.datareference_service.persistence.entity.EntityType;

import java.util.List;
import java.util.Optional;

public interface EntityTypeService {
    List<EntityType> findAllEntityTypes();

    Optional<EntityType> findEntityTypeByUuid(String uuid);

    EntityType getEntityTypeByUuidOrElseThrow(String uuid);
}
