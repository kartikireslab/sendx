/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class Location {

	private String County;
	
	private String PostalCode;
	
	private LocationAdditionalFields AdditionalFields;
	
	public Location() {
		
	}

	public String getCounty() {
		return County;
	}

	public void setCounty(String county) {
		County = county;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}

	public LocationAdditionalFields getAdditionalFields() {
		return AdditionalFields;
	}

	public void setAdditionalFields(LocationAdditionalFields additionalFields) {
		AdditionalFields = additionalFields;
	}

	@Override
	public String toString() {
		return "Location [County=" + County + ", PostalCode=" + PostalCode + ", AdditionalFields=" + AdditionalFields
				+ "]";
	}

	

}
