package com.mcb.datareference_service.controller;


import com.mcb.datareference_service.dto.ResponseFormatDto;
import com.mcb.datareference_service.service.ApplyingPurposeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/data/reference/applying-purposes")
public class ApplyingPurposeController {
    private final ApplyingPurposeService purposeService;

    public ApplyingPurposeController(ApplyingPurposeService applyingPurposeService) {
        this.purposeService = applyingPurposeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseFormatDto> findAllApplyingPurpose() {
        log.info("Get Applying purpose list");
        return ResponseFormatDto.buildResponse(purposeService.getAllApplyingPurpose(),
                HttpStatus.OK);
    }
}