package com.ireslab.sendx.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.google.gson.Gson;
import com.ireslab.sendx.dto.Communication;
import com.ireslab.sendx.dto.DataFields;
import com.ireslab.sendx.dto.DriverLicence;
import com.ireslab.sendx.dto.Location;
import com.ireslab.sendx.dto.LocationAdditionalFields;
import com.ireslab.sendx.dto.NationalId;
import com.ireslab.sendx.dto.PersonInfo;
import com.ireslab.sendx.dto.VerifyRequest;
import com.ireslab.sendx.dto.VerifyResponse;
import com.ireslab.sendx.electra.Status;
import com.ireslab.sendx.entity.Account;
import com.ireslab.sendx.entity.AccountVerification;
import com.ireslab.sendx.entity.Country;
import com.ireslab.sendx.entity.OAuthAccessToken;
import com.ireslab.sendx.entity.OAuthRefreshToken;
import com.ireslab.sendx.entity.Profile;
import com.ireslab.sendx.exception.BusinessException;
import com.ireslab.sendx.model.AccountVerificationResponse;
import com.ireslab.sendx.model.ActivationCodeRequest;
import com.ireslab.sendx.model.ActivationCodeResponse;
import com.ireslab.sendx.model.AgentRequest;
import com.ireslab.sendx.model.AgentResponse;
import com.ireslab.sendx.model.GenericResponse;
import com.ireslab.sendx.model.SignupRequest;
import com.ireslab.sendx.model.SignupResponse;
import com.ireslab.sendx.model.UserProfile;
import com.ireslab.sendx.notification.MailMessage;
import com.ireslab.sendx.notification.MailService;
import com.ireslab.sendx.notification.MailType;
import com.ireslab.sendx.notification.SMSService;
import com.ireslab.sendx.notification.SendxConfig;
import com.ireslab.sendx.repository.AccountRepository;
import com.ireslab.sendx.repository.AccountVerificationRepository;
import com.ireslab.sendx.repository.CountryRepository;
import com.ireslab.sendx.repository.OAuthAccessTokenRepository;
import com.ireslab.sendx.repository.OAuthRefreshTokenRepository;
import com.ireslab.sendx.repository.ProfileRepository;
import com.ireslab.sendx.service.CommonService;
import com.ireslab.sendx.service.ProfileImageService;
import com.ireslab.sendx.service.ProfileService;
import com.ireslab.sendx.service.SignupService;
import com.ireslab.sendx.service.TransactionalApiService;
import com.ireslab.sendx.util.AppStatusCodes;
import com.ireslab.sendx.util.CommonUtils;
import com.ireslab.sendx.util.Constants;
import com.ireslab.sendx.util.PropConstants;

/**
 * @author Nitin
 *
 */
@Service
public class SignupServiceImpl implements SignupService {

	private static final Logger LOG = LoggerFactory.getLogger(SignupServiceImpl.class);

	/**
	 * Electra Api Service
	 */
	@Autowired
	private TransactionalApiService transactionalApiService;

	@Autowired
	private SMSService smsSender;

	@Autowired
	private MailService mailService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SendxConfig sendxConfig;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProfileRepository profileRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private AccountVerificationRepository accVerificationRepo;

	@Autowired
	private ScheduledTransactionExecutor scheduledTransactionExecutor;

	@Autowired
	private ProfileImageService profileImageService;

	@Autowired
	private OAuthAccessTokenRepository accessTokenRepo;

	@Autowired
	private OAuthRefreshTokenRepository refreshTokenRepo;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	Gson gson;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.sendx.service.SignupService#validateMobileNumber(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public GenericResponse validateMobileNumber(Long mobileNumber, String countryCode) {

		GenericResponse genericResponse = null;
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
				countryCode);

		if (account != null) {
			LOG.debug("Mobile Number : " + countryCode + mobileNumber + " is already registered");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.MOBILE_ALREADY_REGISTERED,
					PropConstants.MOBILE_ALREADY_REGISTERED);
		}

		LOG.debug("Mobile Number : " + countryCode + mobileNumber + " is available");
		genericResponse = new GenericResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.MOBILE_AVAILABLE));

		return genericResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.sendx.service.SignupService#verifyAccount(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public AccountVerificationResponse verifyAccount(Long mobileNumber, String countryDialCode) {

		AccountVerificationResponse accVerificationResponse = null;
		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
				countryDialCode);

		// If Mobile number is registered and terminated; allow registration
		// get status from Electra based on user correlation id

		if (account != null) {

			LOG.debug("Account already exists for mobileNumber - " + countryDialCode + mobileNumber
					+ ", getting the account status from electra based on userCorrelationId - "
					+ account.getUserCorrelationId());

			UserProfile userProfile = transactionalApiService.invokeUserProfileAPI(account.getUserCorrelationId());
			LOG.debug("Account status from electra - " + userProfile.getAccountStatus());

			if (userProfile.getAccountStatus().equals(Status.SUSPENDED)) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_SUSPENDED,
						PropConstants.ACCOUNT_SUSPENDED);

			} 
			
			if (userProfile.getAccountStatus().equals(Status.TERMINATED)) {

				String username = countryDialCode + "_" + mobileNumber;

				LOG.debug("Account for mobileNumber - " + username
						+ " is Terminated and is available for re-registration");

				OAuthAccessToken authAccessToken = accessTokenRepo.findByUserName(username);

				if (authAccessToken != null) {
					OAuthRefreshToken authRefreshToken = refreshTokenRepo
							.findByTokenId(authAccessToken.getRefresh_token());

					if (authRefreshToken != null) {
						LOG.debug("Deleting refresh token based on refresh token - "
								+ authAccessToken.getRefresh_token());
						refreshTokenRepo.delete(authRefreshToken);
					}
					LOG.debug("Deleting access token for username - " + authAccessToken.getUserName());
					accessTokenRepo.delete(authAccessToken);
				}

			} else {
				LOG.debug("Account with Mobile Number : " + countryDialCode + mobileNumber + " already exists");
				return new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
						getMessage(PropConstants.ACCOUNT_EXISTS), true);
			}
		}

		LOG.debug("Account with Mobile Number : " + countryDialCode + mobileNumber + " not exists and is available");
		accVerificationResponse = new AccountVerificationResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.ACCOUNT_NOT_EXISTS), false);

		return accVerificationResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.sendx.service.SignupService#requestActivationCode(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ActivationCodeResponse requestActivationCode(Long mobileNumber, String countryCode, String requestType) {

		short requestRetryAttempts = 0;
		String activationCode = null;
		ActivationCodeResponse activationCodeResponse = null;
		Date currentDate = null;

		Integer countryId = commonService.getCountryDetails(countryCode).getCountryId();

		AccountVerification accountVerification = accVerificationRepo
				.findByMobileNumberAndCountryId(BigInteger.valueOf(mobileNumber), countryId);

		if (requestType != null && !requestType.equalsIgnoreCase("signup")) {

			if (accountRepo.findByMobileNumberAndCountry_CountryDialCode(BigInteger.valueOf(mobileNumber),
					countryCode) == null) {
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_NOT_EXISTS,
						PropConstants.ACCOUNT_NOT_EXISTS);
			}
		}

		if (accountVerification != null) {
			requestRetryAttempts = accountVerification.getRetryAttempts();

			// Retry limit reached
			if (requestRetryAttempts <= 0) {
				LOG.error("Retry Limit for Requesting Activation Code reached - " + requestRetryAttempts);
				throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST,
						PropConstants.ACTIVATION_CODE_REQUEST_LIMIT_REACHED);
			}
			requestRetryAttempts -= 1;

		} else {
			currentDate = new Date();
			requestRetryAttempts = sendxConfig.activationCodeRequestRetryLimit;
			accountVerification = new AccountVerification();
			accountVerification.setMobileNumber(BigInteger.valueOf(mobileNumber));
			accountVerification.setCountryId(countryId);
			accountVerification.setCreatedDate(currentDate);
			accountVerification.setModifiedDate(currentDate);
			accountVerification.setVerificationType(Constants.VERIFICATION_TYPE_SIGNUP);
		}

		// Check Test Mode &
		if (sendxConfig.isTestMode) {
			LOG.info("Test Mode is enabled, Activation code configured is - " + Constants.ACTIVATION_CODE);
			activationCode = String.valueOf(Constants.ACTIVATION_CODE);
		} else {

			// Generate Activation Code
			activationCode = String.valueOf(CommonUtils.generateUniqueCode(Constants.ACTIVATION_CODE_LENGTH));
		}

		LOG.debug("Activation code: \n\tRequest Retry Attempts - " + requestRetryAttempts + ", \n\tCode - "
				+ activationCode);

		// Save in database
		currentDate = new Date();
		accountVerification.setCreatedDate(currentDate);
		accountVerification.setModifiedDate(currentDate);
		accountVerification.setActivationCode(activationCode);
		accountVerification.setRetryAttempts(requestRetryAttempts);
		accVerificationRepo.save(accountVerification);

		String userMobileNumber = countryCode.replace("+", "") + String.valueOf(mobileNumber);

		// send Activation Code via SMS
		if (!sendxConfig.isTestMode) {

			String activationCodeMsg = null;
			boolean useShortCodeApi = false;

			if (countryCode.equalsIgnoreCase(Constants.US_COUNTRY_DIAL_CODE)) {
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
		}

		activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				getMessage(PropConstants.ACTIVATION_CODE_SENT), requestRetryAttempts);

		return activationCodeResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.sendx.service.SignupService#validateActivationCode(com.
	 * ireslab.sendx.model.ActivationCodeRequest)
	 */
	@Override
	public ActivationCodeResponse validateActivationCode(@RequestBody ActivationCodeRequest activationCodeRequest) {

		ActivationCodeResponse activationCodeResponse = null;
		LOG.debug("Getting account verification details based on mobile number and country code . . .");

		AccountVerification accountVerification = accVerificationRepo.findByMobileNumberAndCountryId(
				BigInteger.valueOf(activationCodeRequest.getMobileNumber()),
				commonService.getCountryDetails(activationCodeRequest.getCountryDialCode()).getCountryId());

		// No activation code send yet
		if (accountVerification == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}

		/*
		 * Comparing activation code and checking activation code validity
		 */
		//long tempMinCheck=179000;
		if (!activationCodeRequest.getActivationCode().equalsIgnoreCase(accountVerification.getActivationCode())
				|| CommonUtils.calculateTimeDiffInMin(accountVerification.getModifiedDate(),
						new Date()) > sendxConfig.activationCodeValidity) {
			
			

			LOG.error("Activation code invalid or expired !!");

			activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(),
					AppStatusCodes.INVALID_ACTIVATION_CODE, getMessage(PropConstants.INVALID_ACTIVATION_CODE));
			activationCodeResponse.setRetryCounter(accountVerification.getRetryAttempts());
			return activationCodeResponse;
		}
		
		

		LOG.debug("Activation code validated successfully . . . ");
		activationCodeResponse = new ActivationCodeResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
				PropConstants.SUCCESS);
		return activationCodeResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.sendx.service.SignupService#registerAccount(com.ireslab.sendx
	 * .model.SignupRequest)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public SignupResponse registerAccount(SignupRequest signupRequest) {

		String userCorrelationId = null;
		SignupResponse signupResponse = null;
		Date currentDate = new Date();

		/*******************************
		 * USER ACCOUNT CREATION
		 *******************************/

		AccountVerification accountVerification = accVerificationRepo.findByMobileNumberAndCountryId(
				BigInteger.valueOf(signupRequest.getMobileNumber()),
				commonService.getCountryDetails(signupRequest.getCountryDialCode()).getCountryId());

		if (accountVerification == null) {
			LOG.error("Unauthorized request - Activation code validation is required . . . ");
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.INVALID_REQUEST, PropConstants.INVALID_REQUEST);
		}

		Country country = countryRepo
				.findOne(commonService.getCountryDetails(signupRequest.getCountryDialCode()).getCountryId());

		LOG.debug("Initiating User Account creation on Electra Platform ");

		Account account = accountRepo.findByMobileNumberAndCountry_CountryDialCode(
				BigInteger.valueOf(signupRequest.getMobileNumber()), signupRequest.getCountryDialCode());

		if (account == null) {
			account = new Account();
			userCorrelationId = UUID.randomUUID().toString();

		} else if (account != null) {
			userCorrelationId = account.getUserCorrelationId();
			LOG.debug("Updating existing account with new signup details having accountId - " + account.getAccountId());
		}

		signupRequest.setUserCorrelationId(userCorrelationId);

		LOG.debug("Initiating User Account creation on Electra Platform ");
		String uniqueCode =String.valueOf(CommonUtils.generateUniqueCode(Constants.UNIQUE_CODE_LENGTH));
		signupRequest.setUniqueCode(uniqueCode);
		
		//ekyc function
		//truliooEkycEkyb(signupRequest);
		signupRequest.setIsKycConfigure(sendxConfig.isKycConfigure());
		List<com.ireslab.sendx.electra.model.UserProfile> userProfiles = transactionalApiService.invokeUserOnboardingApi(signupRequest);

		// Creating User Account on Electra platform
		if (userProfiles == null || !userProfiles.get(0).isRegistered()) {

			LOG.error("Account Creation on Electra Platform failed");
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.INTERNAL_SERVER_ERROR);
		}
		String profileImageUrl = null;
		LOG.debug("Saving Profile Image on server");
		if(signupRequest.getProfileImageValue() != null && signupRequest.getProfileImageValue() != "") {
			
			profileImageUrl = profileImageService.saveImage("profile", signupRequest.getMobileNumber().toString(),
				signupRequest.getProfileImageValue());
		}

		String idProofUrl = null;
		LOG.debug("Saving ID proof Image on server");
		if(signupRequest.getIdProofImageValue() != null && signupRequest.getIdProofImageValue() != "") {
			idProofUrl = profileImageService.saveImage("idproof", signupRequest.getMobileNumber().toString(),
				signupRequest.getIdProofImageValue());
		}

		account.setMobileNumber(BigInteger.valueOf(signupRequest.getMobileNumber()));
		account.setUserCorrelationId(userCorrelationId);
		account.setCountry(country);
		account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		account.setMpin(passwordEncoder.encode(signupRequest.getmPIN()));
		account.setUniqueCode(uniqueCode);
		account.setStatus(Status.ACTIVE.name());
		account.setDeviceId(signupRequest.getDeviceId());
		account.setDeviceType(signupRequest.getDeviceType());
		account.setCreatedDate(currentDate);
		account.setModifiedDate(currentDate);
		account.setProfileImageUrl(profileImageUrl);
		account.setIdProofImageUrl(idProofUrl);
		account.setResidentialAddress(signupRequest.getResidentialAddress());
		
		
		account = accountRepo.save(account);

		LOG.debug("User Account saved successfully in database" + account.toString());

		Profile userProfile = account.getProfile();
		if (userProfile == null) {
			userProfile = new Profile();
		} else if (userProfile != null) {
			LOG.debug("Updating existing profile with new profile information having accountId - "
					+ userProfile.getProfileId());
		}

		modelMapper.map(signupRequest, userProfile);
		userProfile.setAccount(account);
		userProfile.setCreatedDate(currentDate);
		userProfile.setModifiedDate(currentDate);
		userProfile.setCountry(country);
		
		userProfile = profileRepo.save(userProfile);
		LOG.debug("User Profile saved successfully in database" + userProfile.toString());

		/*******************************
		 * AGENT ACCOUNT CREATION
		 *******************************/

		/*
		 * 
		 * 
		 * Skip Become Agent Function
		 * 
		 * 
		 * */
		/*
		 // Save the agent details on Electra
		if (signupRequest.isLocateAgent()) {
			signupRequest = (SignupRequest) ((AgentRequest) signupRequest)
					.setAgentMobNo(signupRequest.getMobileNumber())
					.setCountryDialCode(signupRequest.getCountryDialCode()).setCorrelationId(userCorrelationId);
			// .setIdProofImageValue(signupRequest.getIdProofImageValue());

			LOG.debug("Signup request contains Agent Registration data");
			AgentResponse agentResponses = transactionalApiService.invokeAgentOnboardingApi(signupRequest,
					account.getUserCorrelationId());

			if (agentResponses == null) {
				LOG.error("Agent account creation on Electra Platform failed");
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, AppStatusCodes.INTERNAL_SERVER_ERROR,
						PropConstants.INTERNAL_SERVER_ERROR);
			}
			LOG.debug("User successfully registered as Agent - " + agentResponses.toString());
		}*/

		/*scheduledTransactionExecutor.executeScheduledTransactions(signupRequest.getMobileNumber(),
				signupRequest.getCountryDialCode());*/
		
		scheduledTransactionExecutor.executeScheduledTransactions(signupRequest.getMobileNumber(),
				signupRequest.getCountryDialCode(),signupRequest.getClientCorrelationId());

		if (accountVerification != null) {
			accVerificationRepo.delete(accountVerification);
		}

		/*
		 * Welcome email to users
		 */
		MailMessage mailMessage = new MailMessage();
		mailMessage.setToEmailAddresses(new String[] { userProfile.getEmailAddress() });
		mailMessage.setMailType(MailType.WELCOME_EMAIL);

		Map<String, Object> msgParams = new HashMap<>();
		msgParams.put("firstName", userProfile.getFirstName());
		msgParams.put("lastName", userProfile.getLastName());
		mailMessage.setModel(msgParams);

		mailService.sendEmail(mailMessage);

		signupResponse = new SignupResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS, PropConstants.SUCCESS);
		signupResponse.setIsRegistered(true);
		if(userProfiles.get(0)!=null) {
		signupResponse.setEkycEkybApproved(userProfiles.get(0).isEkycEkybApproved());
		}
		
		

		return signupResponse;
	}
	
	
	private String truliooEkycEkyb(SignupRequest signupRequest) {
		
		Country country = countryRepo
				.findOne(commonService.getCountryDetails(signupRequest.getCountryDialCode()).getCountryId());
		
		
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(signupRequest.getDob());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		//LOG.debug("Year - "+year+"\n Month - "+(month)+"\n Date - "+day);
		
		PersonInfo personInfo = new PersonInfo();
		personInfo.setFirstGivenName(signupRequest.getFirstName());
		personInfo.setFirstSurName(signupRequest.getLastName());
		personInfo.setGender(String.valueOf(signupRequest.getGender().charAt(0)));
		personInfo.setDayOfBirth(day);
		personInfo.setMonthOfBirth(month);
		personInfo.setYearOfBirth(year);
		
		Communication communication = new Communication();
		communication.setMobileNumber(String.valueOf(signupRequest.getMobileNumber()));
		communication.setEmailAddress(signupRequest.getEmailAddress());
		
		Location location = new Location();
		location.setCounty(country.getCountryName());
		location.setPostalCode(signupRequest.getPostalCode());
		
		LocationAdditionalFields locationAdditionField = new LocationAdditionalFields();
		locationAdditionField.setAddress1(signupRequest.getResidentialAddress());
		location.setAdditionalFields(locationAdditionField);
		
		List<NationalId> nationIdList = new ArrayList<NationalId>();
		NationalId nationalIds = new NationalId();
		nationalIds.setNumber("651357656719");
		nationalIds.setType("Aadhaar Card Number");
		nationIdList.add(nationalIds);
		
		//DriverLicence driverLicence = new DriverLicence();
		//driverLicence.setNumber("UP1420160016588");
		
		DataFields dataFields = new DataFields();
		dataFields.setNationalIds(nationIdList);
		dataFields.setCommunication(communication);
		//dataFields.setDriverLicence(driverLicence);
		dataFields.setPersonInfo(personInfo);
		dataFields.setLocation(location);
		
		VerifyRequest verifyRequest = new VerifyRequest();
		verifyRequest.setCountryCode("HK");
		verifyRequest.setDataFields(dataFields);
		verifyRequest.setDemo(false);
		verifyRequest.setAcceptTruliooTermsAndConditions(true);
		//verifyRequest.setCleansedAddress(false);
		verifyRequest.setConfigurationName("Identity Verification");
		
		
		String verifyRequestJson = gson.toJson(verifyRequest);
		
		LOG.debug("Verify Request : "+verifyRequestJson);
		
		String url = "https://api.globaldatacompany.com/verifications/v1/verify/";
		String  password = "Sendxteam@12345";
		String  username = "SendX_Demo_API";
		
		HttpHeaders headers = createHeaders(username, password);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(verifyRequestJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		
		VerifyResponse verifyResponse = new  VerifyResponse();
		
		verifyResponse = gson.fromJson(response.getBody(), VerifyResponse.class);
		LOG.debug("Verify Response : "+verifyResponse.toString());
		LOG.debug("Response : Status code value -"+response.getStatusCodeValue()+"\n Status Code - "+response.getStatusCode());
		
		return "";
	}
	
	
	private HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	

	/**
	 * @param key
	 * @return
	 */
	private String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}

}
