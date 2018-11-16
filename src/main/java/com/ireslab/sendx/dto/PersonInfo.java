/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class PersonInfo {

	private String FirstGivenName;
	
    private String MiddleName;

	private String FirstSurName;

	private String SecondSurname;

    private String ISOLatin1Name ;

    private Integer DayOfBirth;

	private Integer MonthOfBirth;

    private Integer YearOfBirth;

	private String Gender;
	
	public PersonInfo() {
		
	}

	public String getFirstGivenName() {
		return FirstGivenName;
	}

	public void setFirstGivenName(String firstGivenName) {
		FirstGivenName = firstGivenName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getFirstSurName() {
		return FirstSurName;
	}

	public void setFirstSurName(String firstSurName) {
		FirstSurName = firstSurName;
	}

	public String getSecondSurname() {
		return SecondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		SecondSurname = secondSurname;
	}

	public String getISOLatin1Name() {
		return ISOLatin1Name;
	}

	public void setISOLatin1Name(String iSOLatin1Name) {
		ISOLatin1Name = iSOLatin1Name;
	}

	public Integer getDayOfBirth() {
		return DayOfBirth;
	}

	public void setDayOfBirth(Integer dayOfBirth) {
		DayOfBirth = dayOfBirth;
	}

	public Integer getMonthOfBirth() {
		return MonthOfBirth;
	}

	public void setMonthOfBirth(Integer monthOfBirth) {
		MonthOfBirth = monthOfBirth;
	}

	public Integer getYearOfBirth() {
		return YearOfBirth;
	}

	public void setYearOfBirth(Integer yearOfBirth) {
		YearOfBirth = yearOfBirth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	@Override
	public String toString() {
		return "PersonInfo [FirstGivenName=" + FirstGivenName + ", MiddleName=" + MiddleName + ", FirstSurName="
				+ FirstSurName + ", SecondSurname=" + SecondSurname + ", ISOLatin1Name=" + ISOLatin1Name
				+ ", DayOfBirth=" + DayOfBirth + ", MonthOfBirth=" + MonthOfBirth + ", YearOfBirth=" + YearOfBirth
				+ ", Gender=" + Gender + "]";
	}

	
	

}
