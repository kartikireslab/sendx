/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class Communication {

	private String MobileNumber;
	
	private String EmailAddress;
	
	public Communication() {
		// TODO Auto-generated constructor stub
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "Communication [MobileNumber=" + MobileNumber + ", EmailAddress=" + EmailAddress + "]";
	}

	
}
