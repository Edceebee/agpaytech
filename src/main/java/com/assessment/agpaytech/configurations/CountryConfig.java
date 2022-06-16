package com.assessment.agpaytech.configurations;

import com.assessment.agpaytech.models.Country;
import com.assessment.agpaytech.repositories.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class seeds the database using command line runner.
 */
@Configuration
public class CountryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CountryRepository countryRepository) {
        return (args) -> {
            Country country = new Country(null, "Nigeria");
            Country country2 = new Country(null, "London");
            Country country3 = new Country(null, "India");
            Country country4 = new Country(null, "Switzerland");
            Country country5 = new Country(null, "Scotland");
            Country country6 = new Country(null, "England");
            Country country7 = new Country(null, "Algeria");
            Country country8 = new Country(null, "Mauritius");

            countryRepository.save(country);
            countryRepository.save(country2);
            countryRepository.save(country3);
            countryRepository.save(country4);
            countryRepository.save(country5);
            countryRepository.save(country6);
            countryRepository.save(country7);
            countryRepository.save(country8);

        };
    }

}
