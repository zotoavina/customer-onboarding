package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.EntityType;
import com.mcb.datareference_service.persistence.repository.EntityTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntityTypeServiceImplTest {

    @InjectMocks
    private EntityTypeServiceImpl typeService;

    @Mock
    private EntityTypeRepository typeRepository;

    @Test
    void getAllEntityTypes() {
        // arrange
        var et1 = new EntityType();
        et1.setId(1);
        et1.setName("Trust");

        var et2 = new EntityType();
        et2.setId(2);
        et2.setName("Association");

        var entityTypes = List.of(et1, et2);

        when(typeRepository.findAll()).thenReturn(entityTypes);

        // act
        var types = typeService.findAllEntityTypes();

        // assert
        assertEquals(2, types.size());
    }

    @Test
    void getEntityTypeUsingUuidThatExist() {
        // arrange
        var entityType = new EntityType();
        entityType.setId(1);

        when(typeRepository.findByEntityTypeUUID(anyString()))
                .thenReturn(Optional.of(entityType));

        // act
        var et = typeService.getEntityTypeByUuidOrElseThrow(entityType.getEntityTypeUUID());

        // assert
        assertNotNull(et);
        assertEquals(entityType.getId(), et.getId());
    }

    @Test
    void getEntityTypeUsingUuidAndThrowExceptionIfUuidDoesNotExist() {
        // arrange
        String uuid = UUID.randomUUID().toString();
        Mockito.when(typeRepository.findByEntityTypeUUID(uuid))
                .thenReturn(Optional.empty());

        // act and assert
        assertThrows(EntityNotFoundException.class, () -> typeService.getEntityTypeByUuidOrElseThrow(uuid));
    }
}