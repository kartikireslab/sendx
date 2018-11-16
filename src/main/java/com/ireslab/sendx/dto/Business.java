/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class Business {

	 private String BusinessName;

	 private String BusinessRegistrationNumber;

	 private Integer DayOfIncorporation;

	 private Integer MonthOfIncorporation;

	 private Integer YearOfIncorporation;
	
	public Business() {
		
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}

	public String getBusinessRegistrationNumber() {
		return BusinessRegistrationNumber;
	}

	public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
		BusinessRegistrationNumber = businessRegistrationNumber;
	}

	public Integer getDayOfIncorporation() {
		return DayOfIncorporation;
	}

	public void setDayOfIncorporation(Integer dayOfIncorporation) {
		DayOfIncorporation = dayOfIncorporation;
	}

	public Integer getMonthOfIncorporation() {
		return MonthOfIncorporation;
	}

	public void setMonthOfIncorporation(Integer monthOfIncorporation) {
		MonthOfIncorporation = monthOfIncorporation;
	}

	public Integer getYearOfIncorporation() {
		return YearOfIncorporation;
	}

	public void setYearOfIncorporation(Integer yearOfIncorporation) {
		YearOfIncorporation = yearOfIncorporation;
	}

	@Override
	public String toString() {
		return "Business [BusinessName=" + BusinessName + ", BusinessRegistrationNumber=" + BusinessRegistrationNumber
				+ ", DayOfIncorporation=" + DayOfIncorporation + ", MonthOfIncorporation=" + MonthOfIncorporation
				+ ", YearOfIncorporation=" + YearOfIncorporation + "]";
	}

	

}
