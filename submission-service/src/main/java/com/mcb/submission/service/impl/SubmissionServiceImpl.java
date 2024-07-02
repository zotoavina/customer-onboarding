package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.repository.SubmissionRepository;
import com.mcb.submission.service.ApplicationStatusService;
import com.mcb.submission.service.DataRefApiService;
import com.mcb.submission.service.DocumentService;
import com.mcb.submission.service.SubmissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final DocumentService documentService;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository,
                                 DataRefApiService dataRefApiService,
                                 ApplicationStatusService statusService,
                                 DocumentService docServ,
                                 Validator validator) {
        this.submissionRepository = submissionRepository;
        this.dataRefApiService = dataRefApiService;
        applicationStatusService = statusService;
        documentService = docServ;
        this.validator = validator;
    }

    @Override
    public void validateApplicationData(@NonNull Application application) {
        log.info("Validating application's information");
        Objects.requireNonNull(application);
        Set<ConstraintViolation<Application>> violations = validator.validate(application);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Application> violation : violations) {
                sb.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException("Validation failed: " + sb, violations);
        }
        dataRefApiService.checkDataRef(application);
    }

    @Transactional
    @Override
    public @NonNull Application validateAndSaveApplication(@NonNull Application application,
                                                           @NonNull MultipartFile file) throws IOException {
        log.info("Saving customer application: {}", application.getApplicationId());
        validateApplicationData(application);
        var status = applicationStatusService.findByStatusCodeOrElseThrow("SUBMITTED");
        application.setCurrentStatus(status);
        application = submissionRepository.save(application);
        documentService.saveDocument(application, file);
        return application;
    }

    @Override
    public @NonNull Optional<Application> findApplicationByApplicationId(String applicationUUID) {
        log.info("Finding customer application: {}", applicationUUID);
        return submissionRepository.findCustomerApplicationByApplicationId(applicationUUID);
    }


    @Override
    public @NonNull Application findApplicationByApplicationIdOrElseThrow(String applicationUUID) {
        return findApplicationByApplicationId(applicationUUID)
                .orElseThrow(() -> new EntityNotFoundException("No application with uuid identifier " + applicationUUID));
    }
}
