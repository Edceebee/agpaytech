package com.assessment.agpaytech.repositories;

import com.assessment.agpaytech.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Page<Country> findAll(Pageable pageable);

    @Query("select c from Country c where countryName like %?1%")
    Optional<Country> findCountryByCountryName(String countryName);
}
