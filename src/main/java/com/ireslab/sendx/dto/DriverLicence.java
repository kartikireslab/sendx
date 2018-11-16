/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class DriverLicence {

	private String Number = null;

	private Integer DayOfExpiry = null;

    private Integer MonthOfExpiry = null;

	private Integer YearOfExpiry = null;
	  
	 
	public DriverLicence() {
		
	}


	public String getNumber() {
		return Number;
	}


	public void setNumber(String number) {
		Number = number;
	}


	public Integer getDayOfExpiry() {
		return DayOfExpiry;
	}


	public void setDayOfExpiry(Integer dayOfExpiry) {
		DayOfExpiry = dayOfExpiry;
	}


	public Integer getMonthOfExpiry() {
		return MonthOfExpiry;
	}


	public void setMonthOfExpiry(Integer monthOfExpiry) {
		MonthOfExpiry = monthOfExpiry;
	}


	public Integer getYearOfExpiry() {
		return YearOfExpiry;
	}


	public void setYearOfExpiry(Integer yearOfExpiry) {
		YearOfExpiry = yearOfExpiry;
	}


	@Override
	public String toString() {
		return "DriverLicence [Number=" + Number + ", DayOfExpiry=" + DayOfExpiry + ", MonthOfExpiry=" + MonthOfExpiry
				+ ", YearOfExpiry=" + YearOfExpiry + "]";
	}


	
	

}
