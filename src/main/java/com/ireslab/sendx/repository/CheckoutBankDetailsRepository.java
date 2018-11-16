package com.ireslab.sendx.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.sendx.entity.CheckoutBankDetail;

/**
 * @author Nitin
 *
 */
public interface CheckoutBankDetailsRepository extends CrudRepository<CheckoutBankDetail, Integer> {

	/**
	 * @param countryDialCode
	 * @return
	 */
	public List<CheckoutBankDetail> findByCountry_CountryDialCode(String countryDialCode);

}
