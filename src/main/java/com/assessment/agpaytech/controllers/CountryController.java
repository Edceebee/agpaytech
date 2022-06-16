package com.assessment.agpaytech.controllers;

import com.assessment.agpaytech.models.Country;
import com.assessment.agpaytech.models.dtos.CountryDto;
import com.assessment.agpaytech.responses.ApiResponse;
import com.assessment.agpaytech.services.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

    @Autowired
    CountryService countryService;

    @Autowired
    ModelMapper modelMapper;


    /**
     * endpoint to list Countries in batches .
     * @param pageNo to know the number of page after grouping, has a default value of 0
     * @param pageSize to know the size of page you want to group into, has a default value of 5
     * @return a list of Countries with pageNo and pageSize given
     */
    @GetMapping("/allCountries")
    public ResponseEntity<?> getAllCountries(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {
        try {
            List<Country> foundCountries = countryService.getAllCountries(pageNo, pageSize);
            return new ResponseEntity<>(foundCountries, HttpStatus.FOUND);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }


    /**
     * end to get country with country name
     * @param countryName to know the name of the country
     * @return the Country with name given
     */
    @GetMapping("/{countryName}")
    public ResponseEntity<?> getCountryByName(@PathVariable String countryName) {
        try {
            Country foundCountry = countryService.findByCountryName(countryName);
            return new ResponseEntity<>(foundCountry, HttpStatus.FOUND);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }


    /**
     * method to add a country to the database
     * @param countryDto for collecting details t add
     * @return newly added country
     */
    @PostMapping("")
    public ResponseEntity<?> addCountry(@RequestBody CountryDto countryDto) {
        try {
            Country countryRequest = modelMapper.map(countryDto, Country.class);
            Country country = countryService.addCountry(countryRequest);
            return new ResponseEntity<>(country, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException message) {
            return new ResponseEntity<>(new ApiResponse(message.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
