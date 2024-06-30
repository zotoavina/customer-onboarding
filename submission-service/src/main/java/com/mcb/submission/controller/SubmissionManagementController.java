package com.mcb.submission.controller;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.persistence.entity.CustomerApplication;
import com.mcb.submission.service.SubmissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/submission/management")
public class SubmissionManagementController {

    private final SubmissionService submissionService;

    public SubmissionManagementController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/{applicationUUID}")
    public ResponseEntity<ResponseFormatDto> getCustomerApplication(@PathVariable String applicationUUID) {
        log.info("Checking customer application: {}", applicationUUID);
        HttpStatus status = HttpStatus.OK;
        String message = "Success";
        CustomerApplication customerApplication = null;
        try {
            customerApplication = submissionService.findApplicationByApplicationIdOrElseThrow(applicationUUID);
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(customerApplication, status, message);
    }

}
