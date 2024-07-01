package com.mcb.submission.controller;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.mapper.SubmissionMapper;
import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.service.SubmissionManagementService;
import com.mcb.submission.service.SubmissionService;
import com.mcb.submission.service.impl.SubmissionManagementServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

import static com.mcb.submission.utils.KeyConstant.APP_UUID;
import static com.mcb.submission.utils.KeyConstant.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/v1/submission/management")
public class SubmissionManagementController {

    private final SubmissionService submissionService;
    private final SubmissionMapper submissionMapper;
    private final SubmissionManagementService managementService;
    private final SubmissionManagementServiceImpl submissionManagementServiceImpl;

    public SubmissionManagementController(SubmissionService submissionService,
                                          SubmissionManagementService submissionManagementService,
                                          SubmissionMapper mapper, SubmissionManagementServiceImpl submissionManagementServiceImpl) {
        this.submissionService = submissionService;
        managementService = submissionManagementService;
        this.submissionMapper = mapper;
        this.submissionManagementServiceImpl = submissionManagementServiceImpl;
    }

    @GetMapping("/{applicationUUID}")
    public ResponseEntity<ResponseFormatDto> getCustomerApplication(@PathVariable String applicationUUID) {
        log.info("Checking customer application: {}", applicationUUID);
        HttpStatus status = HttpStatus.OK;
        String message = SUCCESS;
        Application application = null;
        try {
            application = submissionService.findApplicationByApplicationIdOrElseThrow(applicationUUID);
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(application, status, message);
    }


    @PatchMapping(value = "/proceed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFormatDto> proceedApplication(@RequestBody Map<String, String> applicationUUID) {
        String uuid = applicationUUID.get(APP_UUID);
        log.info("Proceed application having app uuid {}", uuid);
        HttpStatus status = HttpStatus.OK;
        String message = "Application proceeded";
        try {
            managementService.proceedApplication(uuid);
        } catch (EntityNotFoundException e) {
            log.warn("Proceed application failed, caused by :  {}", e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        } catch (IllegalStateException e) {
            log.warn("Proceed application encounter an error {}", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(null, status, message);
    }

    @PatchMapping(value = "/approve", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFormatDto> approveApplication(@RequestBody Map<String, String> applicationUUID) {
        String uuid = applicationUUID.get("applicationUUID");
        log.info("Approve application having app uuid {}", uuid);
        HttpStatus status = HttpStatus.OK;
        String message = "Application approved";
        try {
            managementService.approveApplication(uuid);
        } catch (EntityNotFoundException e) {
            log.warn("Application approval failed, caused by :  {}", e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        } catch (IllegalStateException e) {
            log.warn("Application approval encounter an error {}", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(null, status, message);
    }

    @PatchMapping(value = "/reject", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFormatDto> rejectApplication(@RequestBody Map<String, String> applicationUUID) {
        String uuid = applicationUUID.get(APP_UUID);
        log.info("Reject application having app uuid {}", uuid);
        HttpStatus status = HttpStatus.OK;
        String message = "Application rejected";
        try {
            managementService.rejectApplication(uuid);
        } catch (EntityNotFoundException e) {
            log.warn("Application rejection failed, caused by :  {}", e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        } catch (IllegalStateException e) {
            log.warn("Application rejection encounter an error {}", e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(null, status, message);
    }


    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseFormatDto> updateApplication(
            @RequestParam @NonNull Map<String, String> submissionParam) {
        Objects.requireNonNull(submissionParam);
        var appUUID = submissionParam.get(APP_UUID);
        log.info("Update customer application having id {} new values: {}"
                , appUUID, submissionParam);
        Application application = submissionMapper.convert(submissionParam);
        application.setApplicationId(appUUID);
        HttpStatus status = HttpStatus.OK;
        String message = "Application updated";
        try {
            managementService.updateCustomerApplication(application);
        } catch (EntityNotFoundException e) {
            log.warn("Failed to update application {} due to {}", appUUID, e.getMessage());
            message = e.getMessage();
            status = HttpStatus.NOT_FOUND;
        } catch (IllegalStateException e) {
            log.warn("Update application failed {} due to {}", appUUID, e.getMessage());
            message = e.getMessage();
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseFormatDto.buildResponse(application, status, message);
    }

    @GetMapping("/submitted")
    public ResponseEntity<ResponseFormatDto> getListOfSubmittedApplications() {
        log.info("Get list of application to be proceeded by processor");
        var applications = submissionManagementServiceImpl.getListOfSubmissionBasedOnStatus("SUBMITTED");
        return ResponseFormatDto.buildResponse(applications, HttpStatus.OK, SUCCESS);
    }

    @GetMapping("/proceeded")
    public ResponseEntity<ResponseFormatDto> getListOfProceededApplications() {
        log.info("Get list of application to be approved or rejected by approver");
        var applications = submissionManagementServiceImpl.getListOfSubmissionBasedOnStatus("PROCEEDED");
        return ResponseFormatDto.buildResponse(applications, HttpStatus.OK, SUCCESS);
    }

    @GetMapping("kpi")
    public ResponseEntity<ResponseFormatDto> getApplicationKpi() {
        log.info("Get application kpi");
        var applications = submissionManagementServiceImpl.getListOfSubmissionBasedOnStatus("PROCEEDED");
        return ResponseFormatDto.buildResponse(applications, HttpStatus.OK, SUCCESS);
    }
}
