package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.CustomerApplication;
import com.mcb.submission.persistence.repository.SubmissionRepository;
import com.mcb.submission.service.ApplicationStatusService;
import com.mcb.submission.service.DataRefApiService;
import com.mcb.submission.service.SubmissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    private final DataRefApiService dataRefApiService;
    private final ApplicationStatusService applicationStatusService;
    private final Validator validator;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
                                 DataRefApiService dataRefApiService,
                                 ApplicationStatusService statusService,
                                 Validator validator) {
        this.submissionRepository = submissionRepository;
        this.dataRefApiService = dataRefApiService;
        applicationStatusService = statusService;
        this.validator = validator;
    }

    @Override
    public void validateApplicationData(@NonNull CustomerApplication customerApplication) {
        log.info("Validating application's information");
        Objects.requireNonNull(customerApplication);
        Set<ConstraintViolation<CustomerApplication>> violations = validator.validate(customerApplication);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CustomerApplication> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException("Validation failed: " + sb, violations);
        }
        dataRefApiService.checkDataRef(customerApplication);
    }

    @Override
    public @NonNull CustomerApplication validateAndSaveApplication(@NonNull CustomerApplication customerApplication) {
        log.info("Saving customer application: {}", customerApplication.getApplicationId());
        validateApplicationData(customerApplication);
        var status = applicationStatusService.findByStatusCodeOrElseThrow("SUBMITTED");
        customerApplication.setCurrentStatus(status);
        return submissionRepository.save(customerApplication);
    }

    @Override
    public @NonNull Optional<CustomerApplication> findApplicationByApplicationId(String applicationUUID) {
        log.info("Finding customer application: {}", applicationUUID);
        return submissionRepository.findCustomerApplicationByApplicationId(applicationUUID);
    }


    @Override
    public @NonNull CustomerApplication findApplicationByApplicationIdOrElseThrow(String applicationUUID) {
        return findApplicationByApplicationId(applicationUUID)
                .orElseThrow(() -> new EntityNotFoundException("No application with uuid identifier " + applicationUUID));
    }
}
