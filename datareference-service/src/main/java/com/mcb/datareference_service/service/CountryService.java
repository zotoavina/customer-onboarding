package com.mcb.datareference_service.service;

import com.mcb.datareference_service.dto.Country;
import lombok.NonNull;

import java.util.List;

public interface CountryService {

    @NonNull
    List<Country> getAllCountries();

    @NonNull
    Country findCountryByNameOrElseThrow(String countryName);
}
