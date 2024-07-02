package com.mcb.submission.controller;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.exception.FileValidationException;
import com.mcb.submission.mapper.SubmissionMapper;
import com.mcb.submission.persistence.entity.Application;
import com.mcb.submission.service.SubmissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/submissions")
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
        Application application = submisionMapper.convert(submissionParam);
        HttpStatus status = HttpStatus.CREATED;
        String message = "Application CREATED";
        try {
            application = submissionService.validateAndSaveApplication(application, file);
        } catch (FileValidationException fve) {
            log.warn("Application submission failed due to invalid file , error {}", fve.getMessage());
            status = HttpStatus.BAD_REQUEST;
            message = fve.getMessage();
        } catch (IOException ioe) {
            log.error("An unkown error occurred while trying application's document, error {}", ioe.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "INTERNAL SERVER ERROR";
        }

        return ResponseFormatDto.buildResponse(application.getApplicationId(),
                status, message);
    }

    @PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseFormatDto> clientChecksApplication(@RequestBody Map<String, String> applicationUUID) {
        String applicationId = applicationUUID.get("applicationUUID");
        log.info("Checking customer application: {}", applicationId);
        HttpStatus status = HttpStatus.OK;
        String message = "Success";
        Application application = null;
        try {
            application = submissionService.findApplicationByApplicationIdOrElseThrow(applicationId);
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseFormatDto.buildResponse(application, status, message);
    }


}
