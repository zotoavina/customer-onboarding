package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.dto.DataCheckRequestDto;
import com.mcb.datareference_service.dto.DataCheckRespDto;
import com.mcb.datareference_service.dto.ResponseFormatDto;
import com.mcb.datareference_service.service.DataRefService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/data/reference")
public class DataRefController {

    private final DataRefService dataRefService;

    public DataRefController(DataRefService refService) {
        dataRefService = refService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseFormatDto> checkDataRefs(@RequestBody @Valid DataCheckRequestDto refs) {
        log.info("Check data ref value {}", refs);
        HttpStatus status = HttpStatus.OK;
        DataCheckRespDto respDto = null;
        String message = "";
        try {
            respDto = dataRefService.checkDataRef(refs);
        } catch (EntityNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(respDto, status, message);
    }

}
