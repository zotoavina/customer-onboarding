package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.repository.ApplicationStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplicationStatusServiceImplTest {

    @Mock
    private ApplicationStatusRepository statusRepository;

    @InjectMocks
    private ApplicationStatusServiceImpl statusService;

    @Test
    void getStatusByCode() {
        // arrange
        var status = new ApplicationStatus();
        when(statusRepository.findApplicationStatusByStatusCode(anyString()))
                .thenReturn(Optional.of(status));

        // act
        var foundStatus = statusService.findByStatusCodeOrElseThrow("APPROVED");

        // assert
        assertNotNull(foundStatus);
    }

    @Test
    void getStatusByCodeThrowsExceptionIfNotFound() {
        // arrange
        when(statusRepository.findApplicationStatusByStatusCode(anyString()))
                .thenReturn(Optional.empty());

        // act and assert
        assertThrows(EntityNotFoundException.class, () -> statusService.findByStatusCodeOrElseThrow("CODE"));
    }

}