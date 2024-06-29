package com.mcb.datareference_service.service.impl;

import com.mcb.datareference_service.dto.Country;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;

    @Mock
    private RestTemplate restTemplate;


    @Test
    void getAllCountry() {
        // arrange
        var countries = new Country[]{new Country("France", "FR"),
                new Country("Germany", "DE")};

        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.ok(countries));

        // act
        var result = countryService.getAllCountries();

        // assert
        assertEquals(2, result.size());
    }

    @Test
    void findCountryByName() {
        // arrange
        var country = new Country("France", "FR");
        when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(ResponseEntity.ok(country));

        // act
        var result = countryService.findCountryByNameOrElseThrow("France");

        //assert
        assertEquals("France", result.name());
    }

    @Test
    void findByCountryByNameWhichDoesNotExistAndExpectException() {
        // arrange
        when(restTemplate.getForEntity(anyString(), any()))
                .thenThrow(new HttpClientErrorException(HttpStatusCode.valueOf(404)));

        // act and assert
        assertThrows(EntityNotFoundException.class,
                () -> countryService.findCountryByNameOrElseThrow("France"));
    }
}