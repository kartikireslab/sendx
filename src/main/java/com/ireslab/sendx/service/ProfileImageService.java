package com.ireslab.sendx.service;

import javax.servlet.http.HttpServletRequest;

import com.ireslab.sendx.model.UserProfile;

public interface ProfileImageService {

	public UserProfile saveProfileImage(UserProfile userProfile, HttpServletRequest request);

	/*
	 * public UserProfile saveIdProofImage(UserProfile userProfile,
	 * HttpServletRequest request);
	 * 
	 * public UserProfile saveResidentialAddressImage(UserProfile userProfile,
	 * HttpServletRequest request);
	 */

	public String saveImage(String imageName, String mobileNumber, String imageBase64);

	// public byte[] getImageDataAsInputStream(String mobileNumber);

}
