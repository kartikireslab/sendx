/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class Passport {

	 private String Mrz1;

	 private String Mrz2;

	 private String Number;

	 private Integer DayOfExpiry;

	 private Integer MonthOfExpiry;

	 private Integer YearOfExpiry;
	  
	public Passport() {
		
	}

	public String getMrz1() {
		return Mrz1;
	}

	public void setMrz1(String mrz1) {
		Mrz1 = mrz1;
	}

	public String getMrz2() {
		return Mrz2;
	}

	public void setMrz2(String mrz2) {
		Mrz2 = mrz2;
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
		return "Passport [Mrz1=" + Mrz1 + ", Mrz2=" + Mrz2 + ", Number=" + Number + ", DayOfExpiry=" + DayOfExpiry
				+ ", MonthOfExpiry=" + MonthOfExpiry + ", YearOfExpiry=" + YearOfExpiry + "]";
	}


	
	
}
