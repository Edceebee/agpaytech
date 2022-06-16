package com.assessment.agpaytech.services;

import com.assessment.agpaytech.models.Country;
import com.assessment.agpaytech.repositories.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    /**
     * method to list Countries in batches .
     * @param pageNo to know the number of page after grouping
     * @param pageSize to know the size of page you want to group into
     * @return a list of Countries with pageNo and pageSize given
     */
    @Override
    public List<Country> getAllCountries(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        log.info("getting list of countries from database");
        Page<Country> pagedResult = countryRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            log.info("countries found");
            return pagedResult.getContent();
        } else {
            throw new IllegalArgumentException("page not found");
        }
    }

    /**
     * method to get country with country name, this method also has partial search functionality
     * @param countryName to know the name of the country
     * @return the Country with name given
     */
    @Override
    public Country findByCountryName(String countryName) {
        Optional<Country> findByCountryName = Optional.ofNullable(countryRepository.findCountryByCountryName(countryName).
                orElseThrow(() -> new IllegalArgumentException("Country not found")));
        return findByCountryName.get();

    }

    /**
     * method to add a country to the database
     * @param country for collecting details t add
     * @return newly added country
     */
    @Override
    public Country addCountry(Country country) {
        Optional<Country> findCountry = countryRepository.findCountryByCountryName(country.getCountryName());
        if (findCountry.isPresent()) {
            throw new IllegalArgumentException("Country already exist");
        }
       return countryRepository.save(country);
    }

}
