package com.mcb.datareference_service.controller;

import com.mcb.datareference_service.dto.Country;
import com.mcb.datareference_service.dto.ResponseFormatDto;
import com.mcb.datareference_service.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/data/reference/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseFormatDto> getAllCountries() {
        var countries = List.of(new Country("Madagascar", "mg"),
                new Country("France", "fr"));
        return ResponseFormatDto.buildResponse(countries, HttpStatus.OK);
    }

    @GetMapping("/name/{countryName}")
    public ResponseEntity<ResponseFormatDto> findCountryByName(@PathVariable String countryName) {
        log.info("check if country exists using name {}", countryName);
        HttpStatus status = HttpStatus.OK;
        Country country = null;
        String message = "";
        try {
            country = countryService.findCountryByNameOrElseThrow(countryName);
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            status = HttpStatus.NOT_FOUND;
            message = e.getMessage();
        }
        return ResponseFormatDto.buildResponse(country, status, message);
    }
}
