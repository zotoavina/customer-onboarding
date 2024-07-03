package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.repository.ApplicationStatusRepository;
import com.mcb.submission.service.ApplicationStatusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApplicationStatusServiceImpl implements ApplicationStatusService {

    private final ApplicationStatusRepository applicationStatusRepository;

    public ApplicationStatusServiceImpl(ApplicationStatusRepository statusRepository) {
        applicationStatusRepository = statusRepository;
    }

    @Override
    public Optional<ApplicationStatus> findByStatusCode(String statusCode) {
        return applicationStatusRepository.findApplicationStatusByStatusCode(statusCode);
    }

    @Override
    public ApplicationStatus findByStatusCodeOrElseThrow(String statusCode) {
        return findByStatusCode(statusCode)
                .orElseThrow(() -> new EntityNotFoundException("No status with code " + statusCode));
    }

    @Override
    public List<ApplicationStatus> findAll() {
        return applicationStatusRepository.findAll();
    }
}
