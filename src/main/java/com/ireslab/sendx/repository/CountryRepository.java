package com.ireslab.sendx.repository;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.sendx.entity.Country;

/**
 * @author Nitin
 *
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {

	Country findCountryByCountryDialCode(String countryDialCode);

}
