package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.BusinessActivity;
import com.mcb.datareference_service.persistence.repository.BusinessActivityRepository;
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
class BusinessActivityServiceImplTest {

    @InjectMocks
    private BusinessActivityServiceImpl activityService;

    @Mock
    private BusinessActivityRepository activityRepository;

    @Test
    void getAllActivity() {
        // arrange
        BusinessActivity ba1 = new BusinessActivity();
        ba1.setActivityName("Banking");
        ba1.setId(1);

        BusinessActivity ba2 = new BusinessActivity();
        ba1.setActivityName("Manufacturing");
        ba1.setId(2);

        var activityList = List.of(ba1, ba2);

        when(activityRepository.findAll()).thenReturn(activityList);

        // act
        var activities = activityService.getAll();

        // assert
        assertNotNull(activities);
        assertEquals(2, activities.size());
    }

    @Test
    void checkAndGetUsingUuidWithAnExistingValues() {
        // arrange
        BusinessActivity ba = new BusinessActivity();

        when(activityRepository.findByActivityUUID(ba.getActivityUUID()))
                .thenReturn(Optional.of(ba));

        // act
        var activity = activityService.checkAndGetUsingUuid(ba.getActivityUUID());

        // assert
        assertEquals(ba, activity);
    }

    @Test
    void checkAndGetUsingBadValuesAndExpectException() {
        // arrange
        String uuid = UUID.randomUUID().toString();
        Mockito.when(activityRepository.findByActivityUUID(anyString()))
                .thenReturn(Optional.empty());

        // act and assert
        assertThrows(EntityNotFoundException.class, () -> activityService.checkAndGetUsingUuid(uuid));
    }
}