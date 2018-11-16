package com.ireslab.sendx.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.sendx.exception.BusinessException;
import com.ireslab.sendx.model.AccountVerificationResponse;
import com.ireslab.sendx.model.ActivationCodeRequest;
import com.ireslab.sendx.model.ActivationCodeResponse;
import com.ireslab.sendx.model.SignupRequest;
import com.ireslab.sendx.model.SignupResponse;
import com.ireslab.sendx.notification.SendxConfig;
import com.ireslab.sendx.service.SignupService;
import com.ireslab.sendx.util.AppStatusCodes;
import com.ireslab.sendx.util.PropConstants;

/**
 * @author Nitin
 *
 */
@RestController
public class SignUpController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private SignupService signupService;

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/checkMobileNumberRegistration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AccountVerificationResponse verifyAccount(
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode)
			throws JsonProcessingException {

		AccountVerificationResponse accVerificationResponse = null;
		LOG.debug("Account verification request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		if (mobileNumber == null || countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		accVerificationResponse = signupService.verifyAccount(mobileNumber, countryDialCode);
		LOG.debug("Account verification response sent - " + objectWriter.writeValueAsString(accVerificationResponse));

		return accVerificationResponse;
	}

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/requestActivationCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivationCodeResponse requestActivationCode(
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode,
			@RequestParam(value = "requestType", required = false) String requestType) throws JsonProcessingException {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.debug("Request Activation code request received - \n\t mobileNumber : " + mobileNumber
				+ ",\n\t countryCode : " + countryDialCode);

		activationCodeResponse = signupService.requestActivationCode(mobileNumber, countryDialCode, requestType);
		LOG.debug("Request Activation code response sent - " + objectWriter.writeValueAsString(activationCodeResponse));

		return activationCodeResponse;
	}

	/**
	 * @param activationCodeRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/validateActivationCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ActivationCodeResponse validateActivationCode(@RequestBody ActivationCodeRequest activationCodeRequest)
			throws JsonProcessingException {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.debug("Validate Activation code request received - "
				+ objectWriter.writeValueAsString(activationCodeRequest));

		activationCodeResponse = signupService.validateActivationCode(activationCodeRequest);
		LOG.debug(
				"Validate Activation code response sent - " + objectWriter.writeValueAsString(activationCodeResponse));

		return activationCodeResponse;
	}

	/**
	 * @param signupRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public SignupResponse registerAccount(@RequestBody SignupRequest signupRequest, HttpServletRequest request)
			throws JsonProcessingException {

		nameRequestThread((signupRequest.getCountryDialCode() + signupRequest.getMobileNumber()), RequestType.SIGNUP);

		//LOG.debug("JSON Request - " + signupRequest.toString());
		LOG.debug("JSON Request - ");
		SignupResponse signupResponse = null;

		signupResponse = signupService.registerAccount(signupRequest);
		LOG.debug("JSON Response - " + objectWriter.writeValueAsString(signupResponse));

		return signupResponse;
	}

	@Autowired
	private SendxConfig sendxConfig;

	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws Exception {

		LOG.debug("SendxController.test() - SendX Application Started" + sendxConfig);

		/* smsSender.sendMessage("919711355293", "Hello"); */
		return "SendX Application Started";
	}

	@RequestMapping(value = "/test-signup", method = RequestMethod.GET)
	public SignupResponse testSignup(@RequestBody SignupRequest signupRequest) throws Exception {

		return null;
	}

}
