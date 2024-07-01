package com.mcb.datareference_service.persistence.repository;

import com.mcb.datareference_service.persistence.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityTypeRepository extends JpaRepository<EntityType, Integer> {

    Optional<EntityType> findByEntityTypeUUID(String entityTypeUUID);

}
