package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.repository.ApplicationStatusRepository;
import com.mcb.submission.service.ApplicationStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusServiceImpl(ApplicationStatusRepository statusRepository) {
        applicationStatusRepository = statusRepository;
    }

    @Override
    public ApplicationStatus findByStatusCodeOrElseThrow(String statusCode) {
        return applicationStatusRepository.findApplicationStatusByStatusCode(statusCode)
                .orElseThrow(() -> new EntityNotFoundException("No status with code " + statusCode));
    }
}
