package com.mcb.submission.service.impl;

import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.persistence.entity.ApplicationStatus;
import com.mcb.submission.persistence.repository.SubmissionRepository;
import com.mcb.submission.service.ApplicationStatusService;
import com.mcb.submission.service.SubmissionManagementService;
import com.mcb.submission.service.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class SubmissionManagementServiceImpl implements SubmissionManagementService {

    private final SubmissionRepository submissionRepository;
    private final SubmissionService submissionService;
    private final ApplicationStatusService statusService;
    private final ApplicationStatusServiceImpl applicationStatusServiceImpl;

    public SubmissionManagementServiceImpl(SubmissionRepository submissionRepository,
                                           ApplicationStatusService applicationStatusService,
                                           SubmissionService submissionService, ApplicationStatusServiceImpl applicationStatusServiceImpl) {
        this.submissionRepository = submissionRepository;
        statusService = applicationStatusService;
        this.submissionService = submissionService;
        this.applicationStatusServiceImpl = applicationStatusServiceImpl;
    }


    @PreAuthorize("hasAnyAuthority('APPROVER', 'PROCESSOR')")
    @Override
    public List<Application> getListOfSubmissionBasedOnStatus(ApplicationStatus status) {
        return submissionRepository.findCustomerApplicationByCurrentStatus(status);
    }

    @Override
    public List<Application> getListOfSubmissionBasedOnStatus(String status) {
        log.info("Getting application having status {}", status);
        var appStatus = statusService.findByStatusCode(status);
        if (appStatus.isEmpty()) return List.of();
        return getListOfSubmissionBasedOnStatus(appStatus.get());
    }

    @PreAuthorize("hasAnyAuthority('PROCESSOR')")
    @Override
    public Application updateCustomerApplication(Application application) {
        log.info("Update application having id {}", application.getApplicationId());
        Application originalApp = submissionService.
                findApplicationByApplicationIdOrElseThrow(application.getApplicationId());
        if (!originalApp.checkStatus("SUBMITTED"))
            throw new IllegalStateException("Already proceeded application can't be updated");
        submissionService.validateApplicationData(application);
        application.setModificationDate(LocalDateTime.now());
        return submissionRepository.save(application);
    }

    @PreAuthorize("hasAnyAuthority('PROCESSOR')")
    @Transactional
    @Override
    public void proceedApplication(String applicationId) {
        log.info("Proceed application having id : {}", applicationId);
        Application application = submissionService
                .findApplicationByApplicationIdOrElseThrow(applicationId);
        if (!application.checkStatus("SUBMITTED"))
            throw new IllegalStateException("Application has already been proceeded");
        var status = statusService.findByStatusCodeOrElseThrow("PROCEEDED");
        application.setCurrentStatus(status);
        application.setModificationDate(LocalDateTime.now());
        submissionRepository.save(application);
    }

    @PreAuthorize("hasAnyAuthority('APPROVER', 'PROCESSOR')")
    @Transactional
    @Override
    public void rejectApplication(String applicationId) {
        log.info("reject application having id : {}", applicationId);
        Application application = submissionService
                .findApplicationByApplicationIdOrElseThrow(applicationId);
        if (application.checkStatus("APPROVED"))
            throw new IllegalStateException("Already approved application can't be rejected");
        var status = statusService.findByStatusCodeOrElseThrow("REJECTED");
        application.setCurrentStatus(status);
        application.setModificationDate(LocalDateTime.now());
        submissionRepository.save(application);
    }

    @PreAuthorize("hasAnyAuthority('APPROVER')")
    @Transactional
    @Override
    public void approveApplication(String applicationId) {
        log.info("approve application having id : {}", applicationId);
        Application application = submissionService
                .findApplicationByApplicationIdOrElseThrow(applicationId);
        if (!application.checkStatus("PROCEEDED"))
            throw new IllegalStateException("Application should be proceeded before approval");
        var status = statusService.findByStatusCodeOrElseThrow("APPROVED");
        application.setCurrentStatus(status);
        application.setModificationDate(LocalDateTime.now());
        submissionRepository.save(application);
    }


    @PreAuthorize("hasAnyAuthority('APPROVER', 'PROCESSOR')")
    public Map<String, Long> getAppKpi() {
        Map<String, Long> appKpi = new HashMap<>();
        var statusList = applicationStatusServiceImpl.findAll();
        List<Object[]> count = submissionRepository.countByStatus();
        for (var status : statusList) {

            Optional<Object[]> foundStatus = count.stream()
                    .filter(c -> status.getStatusCode().equals(c[0]))
                    .findFirst();

            appKpi.put(status.getStatusCode().toLowerCase(), foundStatus.map(s -> (Long) s[1]).orElse(0L));
        }
        return appKpi;
    }

}
