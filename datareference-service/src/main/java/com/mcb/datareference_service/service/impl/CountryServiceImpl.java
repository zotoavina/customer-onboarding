package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.dto.Country;
import com.mcb.datareference_service.exception.ApiCallException;
import com.mcb.datareference_service.service.CountryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    private final RestTemplate restTemplate;

    @Value("${external.endpoint.country}")
    private String apiBaseUrl;

    public CountryServiceImpl(RestTemplate template) {
        this.restTemplate = template;
    }

    @Override
    public @NonNull List<Country> getAllCountries() {
        log.info("Gel list of countries");
        final var url = apiBaseUrl + "/all?fields=name";
        log.info("Url to be called {}", url);
        try {
            ResponseEntity<Country[]> response = restTemplate.getForEntity(url, Country[].class);
            return List.of(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            log.error("Error while getting countries {}", e.getMessage());
            throw new ApiCallException(url, "Error while getting countries " + e.getMessage());
        }
    }

    @Override
    public @NonNull Country findCountryByNameOrElseThrow(String countryName) {
        log.info("Get country using name : {}", countryName);
        final var url = apiBaseUrl + "/name/" + countryName + "?fields=name&fullText=true";
        try {
            ResponseEntity<Country[]> response = restTemplate.getForEntity(url, Country[].class);
            Country foundCountry = (Objects.isNull(response.getBody())) ? null : response.getBody()[0];
            return Optional.ofNullable(foundCountry)
                    .orElseThrow(() -> new EntityNotFoundException("No country with name " + countryName));
        } catch (HttpClientErrorException e) {
            log.warn("Error while fetching country with name {}, error {}", countryName, e.getMessage());
            if (e.getStatusCode().is4xxClientError()) {
                throw new EntityNotFoundException("no Country with name " + countryName);
            } else {
                throw new ApiCallException(url, "Error while fetching country with name " + countryName);
            }
        } catch (Exception e) {
            log.error("Error while getting country {}", e.getMessage());
            throw new ApiCallException(url, "Error while getting country using name" + e.getMessage());
        }
    }
}
