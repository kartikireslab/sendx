/**
 * 
 */
package com.ireslab.sendx.dto;

import java.util.List;
import java.util.Map;

/**
 * @author ireslab
 *
 */
public class DataFields {

	  private PersonInfo PersonInfo;

	  private Location Location;

	  private Communication Communication;

	  private DriverLicence DriverLicence;

	  private List<NationalId> NationalIds;

	  private Passport Passport;

	  private Document Document;

	  private Business Business;

	  private Map<String, Map<String, String>> CountrySpecific;
	
	public DataFields() {
		
	}

	public PersonInfo getPersonInfo() {
		return PersonInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		PersonInfo = personInfo;
	}

	public Location getLocation() {
		return Location;
	}

	public void setLocation(Location location) {
		Location = location;
	}

	public Communication getCommunication() {
		return Communication;
	}

	public void setCommunication(Communication communication) {
		Communication = communication;
	}

	public DriverLicence getDriverLicence() {
		return DriverLicence;
	}

	public void setDriverLicence(DriverLicence driverLicence) {
		DriverLicence = driverLicence;
	}

	public List<NationalId> getNationalIds() {
		return NationalIds;
	}

	public void setNationalIds(List<NationalId> nationalIds) {
		NationalIds = nationalIds;
	}

	public Passport getPassport() {
		return Passport;
	}

	public void setPassport(Passport passport) {
		Passport = passport;
	}

	public Document getDocument() {
		return Document;
	}

	public void setDocument(Document document) {
		Document = document;
	}

	public Business getBusiness() {
		return Business;
	}

	public void setBusiness(Business business) {
		Business = business;
	}

	public Map<String, Map<String, String>> getCountrySpecific() {
		return CountrySpecific;
	}

	public void setCountrySpecific(Map<String, Map<String, String>> countrySpecific) {
		CountrySpecific = countrySpecific;
	}

	@Override
	public String toString() {
		return "DataFields [PersonInfo=" + PersonInfo + ", Location=" + Location + ", Communication=" + Communication
				+ ", DriverLicence=" + DriverLicence + ", NationalIds=" + NationalIds + ", Passport=" + Passport
				+ ", Document=" + Document + ", Business=" + Business + ", CountrySpecific=" + CountrySpecific + "]";
	}

	
}
