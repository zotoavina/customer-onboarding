package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.persistence.entity.ApplyingPurpose;
import com.mcb.datareference_service.persistence.repository.ApplyingPurposeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyingPurposeServiceImplTest {

    @InjectMocks
    private ApplyingPurposeServiceImpl applyingPurposeService;

    @Mock
    private ApplyingPurposeRepository purposeRepository;

    @Test
    void getAllApplyingPurposes() {
        // arrange
        var purpose1 = new ApplyingPurpose();
        purpose1.setId(1);
        purpose1.setPurposeName("Investment porfolio");

        var purposes = List.of(purpose1);

        when(purposeRepository.findAll())
                .thenReturn(purposes);
        // act
        var purposeList = applyingPurposeService.getAllApplyingPurpose();

        // assert
        assertNotNull(purposeList);
        assertEquals(1, purposeList.size());
    }

    @Test
    void getPurposeUsingUuidThatExists() {
        // arrange
        var purpose = new ApplyingPurpose();
        purpose.setId(2);

        when(purposeRepository.findByPurposeUUID(anyString()))
                .thenReturn(Optional.of(purpose));

        // act
        var foundPurpose = applyingPurposeService.getApplyingPurposeByUuidOrElseThrow(purpose.getPurposeUUID());

        // assert
        assertEquals(purpose.getId(), foundPurpose.getId());
    }

    @Test
    void getPurposeUsingBadUuidAndExpectException() {
        // arrange
        String uuid = UUID.randomUUID().toString();

        when(purposeRepository.findByPurposeUUID(uuid))
                .thenReturn(Optional.empty());

        // act and assert
        assertThrows(EntityNotFoundException.class,
                () -> applyingPurposeService.getApplyingPurposeByUuidOrElseThrow(uuid));
    }
}