package com.ireslab.sendx.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest extends AgentRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410246179154799396L;

	private String userCorrelationId;

	private String firstName;
	private String middleName;
	private String lastName;
	private String countryDialCode;
	private Long mobileNumber;
	private String secondaryContactNumber;
	private String contactAddress;
	private String password;
	private String kycIdTitle;
	private String kycIdFront;
	private String kycIdBack;
	private Map<String, String> kycIdDetails;
	private String deviceId;
	private String deviceType;
	private String emailAddress;
	private String mPIN;
	private Boolean isLocateAgent;
	private String companyName;
	private String companyCode;
	private String clientCorrelationId;
	private Boolean isKycConfigure;
	
	//private String profileImageValue;
	/* kyc updation */

	/*
	 * private String profileImageValue; private String idProofImageValue;
	 */

	private String residentialAddress;
	
	private String dob;
	
	private String gender;
	
	private String scanDocumentType;
	
	private String scanDocumentId;
	
	private String scanDocumentFrontPage;
	
	private String scanDocumentBackPage;
	
	private String postalCode;
	
	private String currencyType;
	
	private String uniqueCode;
	
	//private String bussinessLat;
	
	//private String bussinessLong;

	
	public String getUserCorrelationId() {
		return userCorrelationId;
	}

	public String getUniqueCode() {
		return uniqueCode;
	}

	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}

	public void setUserCorrelationId(String userCorrelationId) {
		this.userCorrelationId = userCorrelationId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountryDialCode() {
		return countryDialCode;
	}

	public AgentRequest setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
		return this;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKycIdTitle() {
		return kycIdTitle;
	}

	public void setKycIdTitle(String kycIdTitle) {
		this.kycIdTitle = kycIdTitle;
	}

	public String getKycIdFront() {
		return kycIdFront;
	}

	public void setKycIdFront(String kycIdFront) {
		this.kycIdFront = kycIdFront;
	}

	public String getKycIdBack() {
		return kycIdBack;
	}

	public void setKycIdBack(String kycIdBack) {
		this.kycIdBack = kycIdBack;
	}

	public Map<String, String> getKycIdDetails() {
		return kycIdDetails;
	}

	public void setKycIdDetails(Map<String, String> kycIdDetails) {
		this.kycIdDetails = kycIdDetails;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getmPIN() {
		return mPIN;
	}

	public void setmPIN(String mPIN) {
		this.mPIN = mPIN;
	}

	public Boolean isLocateAgent() {
		return isLocateAgent;
	}

	public void setIsLocateAgent(Boolean isLocateAgent) {
		this.isLocateAgent = isLocateAgent;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getClientCorrelationId() {
		return clientCorrelationId;
	}

	public void setClientCorrelationId(String clientCorrelationId) {
		this.clientCorrelationId = clientCorrelationId;
	}

	
	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getScanDocumentType() {
		return scanDocumentType;
	}

	public void setScanDocumentType(String scanDocumentType) {
		this.scanDocumentType = scanDocumentType;
	}

	public String getScanDocumentId() {
		return scanDocumentId;
	}

	public void setScanDocumentId(String scanDocumentId) {
		this.scanDocumentId = scanDocumentId;
	}

	public String getScanDocumentFrontPage() {
		return scanDocumentFrontPage;
	}

	public void setScanDocumentFrontPage(String scanDocumentFrontPage) {
		this.scanDocumentFrontPage = scanDocumentFrontPage;
	}

	public String getScanDocumentBackPage() {
		return scanDocumentBackPage;
	}

	public void setScanDocumentBackPage(String scanDocumentBackPage) {
		this.scanDocumentBackPage = scanDocumentBackPage;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	
	public Boolean getIsKycConfigure() {
		return isKycConfigure;
	}

	public void setIsKycConfigure(Boolean isKycConfigure) {
		this.isKycConfigure = isKycConfigure;
	}

	@Override
	public String toString() {
		return "SignupRequest [userCorrelationId=" + userCorrelationId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", countryDialCode=" + countryDialCode + ", mobileNumber="
				+ mobileNumber + ", secondaryContactNumber=" + secondaryContactNumber + ", contactAddress="
				+ contactAddress + ", password=" + password + ", kycIdTitle=" + kycIdTitle + ", kycIdFront="
				+ kycIdFront + ", kycIdBack=" + kycIdBack + ", kycIdDetails=" + kycIdDetails + ", deviceId=" + deviceId
				+ ", deviceType=" + deviceType + ", emailAddress=" + emailAddress + ", mPIN=" + mPIN
				+ ", isLocateAgent=" + isLocateAgent + ", companyName=" + companyName + ", companyCode=" + companyCode
				+ ", clientCorrelationId=" + clientCorrelationId + ", isKycConfigure=" + isKycConfigure
				+ ", residentialAddress=" + residentialAddress + ", dob=" + dob + ", gender=" + gender
				+ ", scanDocumentType=" + scanDocumentType + ", scanDocumentId=" + scanDocumentId + ", postalCode="
				+ postalCode + ", currencyType=" + currencyType + "]";
	}

	

	

	
	
	
}
