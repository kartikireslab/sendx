package com.ireslab.sendx.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.sendx.electra.model.ExchangeResponse;
import com.ireslab.sendx.exception.BusinessException;
import com.ireslab.sendx.model.CheckoutBanksResponse;
import com.ireslab.sendx.model.ContactListVerificationRequest;
import com.ireslab.sendx.model.ContactListVerificationResponse;
import com.ireslab.sendx.model.CountryListResponse;
import com.ireslab.sendx.model.GenericResponse;
import com.ireslab.sendx.model.MiscConfigDetailsResponse;
import com.ireslab.sendx.notification.MailMessage;
import com.ireslab.sendx.notification.MailService;
import com.ireslab.sendx.notification.MailType;
import com.ireslab.sendx.notification.SMSService;
import com.ireslab.sendx.service.CommonService;
import com.ireslab.sendx.service.TransactionalApiService;
import com.ireslab.sendx.util.AppStatusCodes;
import com.ireslab.sendx.util.Constants;
import com.ireslab.sendx.util.PropConstants;

/**
 * @author Nitin
 *
 */
@RestController
public class GenericController {

	private static final Logger LOG = LoggerFactory.getLogger(GenericController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private CommonService commonService;

	@Autowired
	private TransactionalApiService transactionService;

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/allCountryDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CountryListResponse getAllCountryDetails() throws JsonProcessingException {

		CountryListResponse countryListResponse = null;
		LOG.debug("Getting Supported countries details request received . . . ");

		countryListResponse = commonService.getAllCountryDetails();
		LOG.debug("Getting Supported countries details response sent . . . ");
		/* + objectWriter.writeValueAsString(countryListResponse)); */

		return countryListResponse;
	}

	@RequestMapping(value = "/getExchangeDetails", method = RequestMethod.GET)
	public ResponseEntity<ExchangeResponse> getExchangeDetails(
			@RequestParam(value = "userCorrelationId", required = false) String userCorrelationId)
			throws JsonProcessingException {

		LOG.info("Exchange details  request received  : ");
		ExchangeResponse exchangeDetailsResponse = transactionService.getAllExchangeDetails(userCorrelationId);
		return new ResponseEntity<>(exchangeDetailsResponse, HttpStatus.OK);
	}

	/**
	 * @param contactListVerificationRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/verifyContactList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ContactListVerificationResponse validateContacts(
			@RequestBody ContactListVerificationRequest contactListVerificationRequest) throws JsonProcessingException {

		ContactListVerificationResponse contactListVerificationResponse = null;
		LOG.debug("Contact List Verification request received . . . "
				+ contactListVerificationRequest.getCountryDialCode());

		contactListVerificationResponse = commonService.validateContacts(contactListVerificationRequest);
		LOG.debug("Contact List Verification response sent . . . "
																	 +objectWriter.writeValueAsString(
																	 contactListVerificationResponse)
																	 );

		return contactListVerificationResponse;
	}

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/allCheckoutBankDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CheckoutBanksResponse getCheckoutBanks(
			@RequestParam(value = "countryDialCode", required = true) String countryDialCode)
			throws JsonProcessingException {

		CheckoutBanksResponse checkoutBanksResponse = null;
		LOG.debug("Getting supported checkout bank details based on countryCode : " + countryDialCode);

		if (countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		checkoutBanksResponse = commonService.getCheckoutBanksDetails(countryDialCode);
		LOG.debug("Getting supported checkout bank details response sent - "
				+ objectWriter.writeValueAsString(checkoutBanksResponse));

		return checkoutBanksResponse;
	}

	/**
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/miscConfigDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MiscConfigDetailsResponse getSendxConfigDetails(
			@RequestParam(value = "mobileNumber", required = false) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = false) String countryDialCode)
			throws JsonProcessingException {

		return commonService.getMiscConfigDetails(BigInteger.valueOf(mobileNumber), countryDialCode);
	}

	@Autowired
	SMSService smsSender;

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testSMS", method = RequestMethod.GET)
	public GenericResponse test(@RequestParam(value = "mobileNumber", required = false) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = false) String countryDialCode) throws Exception {

		String userMobileNumber = countryDialCode.replace("+", "") + String.valueOf(mobileNumber);
		String activationCode = "123456";

		boolean useShortCodeApi = false;
		String activationCodeMsg = null;

		if (countryDialCode.equalsIgnoreCase(Constants.US_COUNTRY_DIAL_CODE)) {
			useShortCodeApi = true;
			activationCodeMsg = String.format(smsSender.getShortCodeApiActivationCodeMessage(), activationCode);

		} else {
			activationCodeMsg = String.format(smsSender.getActivationCodeMessage(), activationCode);
		}

		if (!smsSender.sendMessage(userMobileNumber, activationCodeMsg, useShortCodeApi)) {

			LOG.error("Error occurred while sending Activation Code SMS message . . . .");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.INTERNAL_SERVER_ERROR);
		}

		/* smsSender.sendMessage("919711355293", "Hello"); */
		return new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
	}

	@Autowired
	MailService mailService;

	/**
	 * @param emailAddress
	 * @return
	 */
	@RequestMapping(value = "/testMail", method = RequestMethod.GET)
	public GenericResponse testMail(@RequestParam(value = "emailAddress") String emailAddress) {

		System.out.println("GenericController.testMail()");

		MailMessage mailMessage = new MailMessage();
		mailMessage.setToEmailAddresses(new String[] { emailAddress });

		Map<String, Object> model = new HashMap<>();
		model.put("firstName", "Nitin");
		model.put("lastName", "Malik");

		mailMessage.setModel(model);
		/*
		 * mailMessage.setSubject("asdakdnaskdnsa");
		 * mailMessage.setFromAddress("noreply@sendxsg.com");
		 */
		mailMessage.setMailType(MailType.WELCOME_EMAIL);
		mailService.sendEmail(mailMessage);

		/* mailSender.sendMail(mailMessage); */
		return new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
	}
}
