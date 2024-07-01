package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.EntityType;
import com.mcb.datareference_service.persistence.repository.EntityTypeRepository;
import com.mcb.datareference_service.service.EntityTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EntityTypeServiceImpl implements EntityTypeService {

    private final EntityTypeRepository entityTypeRepository;

    public EntityTypeServiceImpl(EntityTypeRepository typeRepository) {
        entityTypeRepository = typeRepository;
    }

    @Override
    public List<EntityType> findAllEntityTypes() {
        log.info("Get all entity type");
        return entityTypeRepository.findAll();
    }

    @Override
    public Optional<EntityType> findEntityTypeByUuid(String uuid) {
        return entityTypeRepository.findByEntityTypeUUID(uuid);
    }

    @Override
    public EntityType getEntityTypeByUuidOrElseThrow(String uuid) {
        log.info("Get and check entity type using uuid {}", uuid);
        return findEntityTypeByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("No entity type with uuid identifier " + uuid));
    }
}
