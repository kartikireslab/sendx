package com.ireslab.sendx.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ireslab.sendx.entity.Account;
import com.ireslab.sendx.repository.AccountRepository;
import com.ireslab.sendx.service.AccountService;

/**
 * @author Nitin
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.sendx.service.AccountService#getAccount(java.math.BigInteger,
	 * java.lang.String)
	 */
	@Override
	public Account getAccount(BigInteger mobileNumber, String countryDialCode) {
		return accountRepository.findByMobileNumberAndCountry_CountryDialCode(mobileNumber, countryDialCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.sendx.service.AccountService#getAccountByUniqueCode(java.lang
	 * .String)
	 */
	@Override
	public Account getAccountByUniqueCode(String uniqueCode) {
		return accountRepository.findByUniqueCode(uniqueCode);
	}

}
