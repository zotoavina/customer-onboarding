package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.dto.ResponseFormatDto;
import com.mcb.datareference_service.service.EntityTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/data/reference/entity-types")
public class EntityTypeController {

    private final EntityTypeService entityTypeService;

    public EntityTypeController(EntityTypeService typeService) {
        entityTypeService = typeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseFormatDto> findAllEntityTypes() {
        log.info("Get all entity types");
        return ResponseFormatDto.buildResponse(entityTypeService.findAllEntityTypes(),
                HttpStatus.OK);
    }

}
