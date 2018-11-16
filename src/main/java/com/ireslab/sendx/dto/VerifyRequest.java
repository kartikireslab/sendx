/**
 * 
 */
package com.ireslab.sendx.dto;

import java.util.List;

/**
 * @author ireslab
 *
 */
public class VerifyRequest {

	  private String CallBackUrl;

	  private Integer Timeout;

	  private Boolean CleansedAddress;

	  private String ConfigurationName;
	  
	  private Boolean AcceptTruliooTermsAndConditions;
	  
	  private Boolean Demo;

	  private List<String> ConsentForDataSources;

	  private String CountryCode;

	  private DataFields DataFields;
	
	public VerifyRequest() {
		
	}

	public String getCallBackUrl() {
		return CallBackUrl;
	}

	public void setCallBackUrl(String callBackUrl) {
		CallBackUrl = callBackUrl;
	}

	public Integer getTimeout() {
		return Timeout;
	}

	public void setTimeout(Integer timeout) {
		Timeout = timeout;
	}

	public Boolean getCleansedAddress() {
		return CleansedAddress;
	}

	public void setCleansedAddress(Boolean cleansedAddress) {
		CleansedAddress = cleansedAddress;
	}

	public String getConfigurationName() {
		return ConfigurationName;
	}

	public void setConfigurationName(String configurationName) {
		ConfigurationName = configurationName;
	}

	public Boolean getAcceptTruliooTermsAndConditions() {
		return AcceptTruliooTermsAndConditions;
	}

	public void setAcceptTruliooTermsAndConditions(Boolean acceptTruliooTermsAndConditions) {
		AcceptTruliooTermsAndConditions = acceptTruliooTermsAndConditions;
	}

	public Boolean getDemo() {
		return Demo;
	}

	public void setDemo(Boolean demo) {
		Demo = demo;
	}

	public List<String> getConsentForDataSources() {
		return ConsentForDataSources;
	}

	public void setConsentForDataSources(List<String> consentForDataSources) {
		ConsentForDataSources = consentForDataSources;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public DataFields getDataFields() {
		return DataFields;
	}

	public void setDataFields(DataFields dataFields) {
		DataFields = dataFields;
	}

	@Override
	public String toString() {
		return "VerifyRequest [CallBackUrl=" + CallBackUrl + ", Timeout=" + Timeout + ", CleansedAddress="
				+ CleansedAddress + ", ConfigurationName=" + ConfigurationName + ", AcceptTruliooTermsAndConditions="
				+ AcceptTruliooTermsAndConditions + ", Demo=" + Demo + ", ConsentForDataSources="
				+ ConsentForDataSources + ", CountryCode=" + CountryCode + ", DataFields=" + DataFields + "]";
	}
	

	

}
