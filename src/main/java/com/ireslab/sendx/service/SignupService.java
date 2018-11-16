package com.ireslab.sendx.service;

import com.ireslab.sendx.model.AccountVerificationResponse;
import com.ireslab.sendx.model.ActivationCodeRequest;
import com.ireslab.sendx.model.ActivationCodeResponse;
import com.ireslab.sendx.model.GenericResponse;
import com.ireslab.sendx.model.SignupRequest;
import com.ireslab.sendx.model.SignupResponse;

/**
 * @author Nitin
 *
 */
public interface SignupService {

	public AccountVerificationResponse verifyAccount(Long mobileNumber, String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public GenericResponse validateMobileNumber(Long mobileNumber, String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public ActivationCodeResponse requestActivationCode(Long mobileNumber, String countryDialCode, String requestType);

	/**
	 * @param activationCodeRequest
	 * @return
	 */
	public ActivationCodeResponse validateActivationCode(ActivationCodeRequest activationCodeRequest);

	/**
	 * @param signupRequest
	 * @return
	 */
	public SignupResponse registerAccount(SignupRequest signupRequest);

}
