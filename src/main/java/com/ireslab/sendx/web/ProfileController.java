package com.ireslab.sendx.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.sendx.exception.BusinessException;
import com.ireslab.sendx.model.GenericResponse;
import com.ireslab.sendx.model.UserProfile;
import com.ireslab.sendx.service.ProfileService;
import com.ireslab.sendx.springsecurity.SpringSecurityUtil;
import com.ireslab.sendx.util.AppStatusCodes;
import com.ireslab.sendx.util.PropConstants;

/**
 * @author Nitin
 *
 */
@RestController
public class ProfileController {

	private static final Logger LOG = LoggerFactory.getLogger(ProfileController.class);

	@Autowired
	private ProfileService profileService;

	@Autowired
	private ObjectWriter objectWriter;

	/**
	 * @param userProfile
	 * @return
	 */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile updateProfile(@RequestBody UserProfile userProfile, HttpServletRequest request) {

		LOG.debug("Request received for profile updation - " + userProfile.toString());

		// Getting username details from Spring Security Context
		String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		userProfile.setMobileNumber(mobileNumber);
		userProfile.setCountryDialCode(countryDialCode);

		return profileService.editUserProfile(userProfile, request);
	}

	/**
	 * @param mobileNumber
	 * @param countryCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getProfile", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getProfile() throws JsonProcessingException {

		// Getting username details from Spring Security Context
		String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		UserProfile userProfile = null;
		LOG.debug("Get user profile request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		if (mobileNumber == null || countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		userProfile = profileService.getUserProfile(mobileNumber, countryDialCode);
		LOG.debug("Get user profile response sent - " + objectWriter.writeValueAsString(userProfile));

		return userProfile;
	}

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getProfile/{uniqueCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserProfile getProfileByUniqueCode(@PathVariable("uniqueCode") String uniqueCode)
			throws JsonProcessingException {

		UserProfile userProfile = null;
		LOG.debug("Get user profile request received for unique code - " + uniqueCode);

		userProfile = profileService.getUserProfileByUniqueCode(uniqueCode);
		LOG.debug("Get user profile response sent - " + objectWriter.writeValueAsString(userProfile));
		return userProfile;
	}

	/**
	 * @param userProfile
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updatePassword(@RequestBody UserProfile userProfile) throws JsonProcessingException {

		GenericResponse genericResponse = null;
		String mobileNumber = userProfile.getCountryDialCode() + userProfile.getMobileNumber();
		LOG.debug("Password update request received for Mobile Number - " + mobileNumber);

		genericResponse = profileService.updatePasswordOrMpin(userProfile);
		LOG.debug("Password successfully updated for Mobile Number - " + mobileNumber);
		return genericResponse;
	}

	/**
	 * @param userProfile
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/updateMpin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse updateMpin(@RequestBody UserProfile userProfile) throws JsonProcessingException {

		GenericResponse genericResponse = null;
		String mobileNumber = userProfile.getCountryDialCode() + userProfile.getMobileNumber();
		LOG.debug("mPIN update request received for Mobile Number - " + mobileNumber);

		genericResponse = profileService.updatePasswordOrMpin(userProfile);
		LOG.debug("mPIN successfully updated for Mobile Number - " + mobileNumber);
		return genericResponse;
	}

	// @GetMapping(value = "/getProfileImage", produces =
	// MediaType.IMAGE_JPEG_VALUE)
	// public @ResponseBody byte[] getImageWithMediaType(HttpServletRequest request,
	// @RequestParam("imageName") String imageName) throws IOException {
	// LOG.debug("Request recived to fetching image[" + imageName + "]");
	// return profileImageService.getImageDataAsInputStream(imageName);
	// }
}
