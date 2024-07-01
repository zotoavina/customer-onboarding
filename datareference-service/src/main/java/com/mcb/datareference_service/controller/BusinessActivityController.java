package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.dto.ResponseFormatDto;
import com.mcb.datareference_service.service.BusinessActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/data/reference/activities")
public class BusinessActivityController {
    private final BusinessActivityService activityService;

    public BusinessActivityController(BusinessActivityService businessActivityService) {
        this.activityService = businessActivityService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseFormatDto> findAllActivities() {
        log.info("Get all business activities");
        return ResponseFormatDto.buildResponse(activityService.getAll(),
                HttpStatus.OK);
    }
}
