package com.mcb.submission.controller;

import com.mcb.submission.dto.ResponseFormatDto;
import com.mcb.submission.mapper.SubmissionMapper;
import com.mcb.submission.persistence.entity.CustomerApplication;
import com.mcb.submission.service.SubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        HttpStatus status = HttpStatus.OK;
        String message = "Success";
        try {
            submissionService.validateAndSaveApplication(customerApplication);
        } catch (Exception e) {
            log.error("Error while saving application", e);
            message = e.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseFormatDto.buildResponse(null, status, message);
    }
}
