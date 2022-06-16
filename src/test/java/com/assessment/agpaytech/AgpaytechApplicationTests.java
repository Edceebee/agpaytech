package com.assessment.agpaytech;

import com.assessment.agpaytech.models.Country;
import com.assessment.agpaytech.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AgpaytechApplicationTests {

	@Autowired
	CountryService countryService;

	@Test
	@Order(1)
	void testToGetAllCountries() {
		List<Country> foundCountries = countryService.getAllCountries(0, 5);
		assertThat((long) foundCountries.size()).isEqualTo(5);
	}

	@Test
	@Order(2)
	void testToFindCountryByName() {
		Country foundCountry = countryService.findByCountryName("Nigeria");

		assertThat(foundCountry.getId()).isEqualTo(1);
		assertThat(foundCountry.getCountryName()).isEqualTo("Nigeria");
	}

	@Test
	@Order(3)
	void testToAddCountry() {
		Country country = new Country(null, "South Africa");
		Country foundCountry = countryService.addCountry(country);

		assertThat(foundCountry).hasNoNullFieldsOrProperties();
		assertThat(foundCountry).isNotNull();
		assertThat(foundCountry.getCountryName()).isEqualTo("South Africa");
	}
}
