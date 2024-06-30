package com.mcb.submission.controller;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.mapper.SubmissionMapper;
import com.mcb.submission.persistence.entity.CustomerApplication;
import com.mcb.submission.service.SubmissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {

    private final SubmissionMapper submisionMapper;

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionMapper submisionMapper, SubmissionService submissionService) {
        this.submisionMapper = submisionMapper;
        this.submissionService = submissionService;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseFormatDto> submit(@RequestParam MultipartFile file,
                                                    @RequestParam Map<String, String> submissionParam) {
        log.info("Saving customer application: {}", submissionParam);
        CustomerApplication customerApplication = submisionMapper.convert(submissionParam);
        customerApplication = submissionService.validateAndSaveApplication(customerApplication);
        return ResponseFormatDto.buildResponse(customerApplication.getApplicationId(),
                HttpStatus.CREATED, "Created");
    }

    @PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFormatDto> clientChecksApplication(@RequestBody Map<String, String> applicationUUID) {
        String applicationId = applicationUUID.get("applicationUUID");
        log.info("Checking customer application: {}", applicationId);
        HttpStatus status = HttpStatus.OK;
        String message = "Success";
        CustomerApplication customerApplication = null;
        try {
            customerApplication = submissionService.findApplicationByApplicationIdOrElseThrow(applicationId);
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseFormatDto.buildResponse(customerApplication, status, message);
    }


}
