package com.assessment.agpaytech.services;

import com.assessment.agpaytech.models.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountries(Integer pageNo, Integer pageSize);

    List<Country> findByCountryName(String countryName);

    Country addCountry(Country country);
}
