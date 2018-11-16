/**
 * 
 */
package com.ireslab.sendx.dto;

/**
 * @author ireslab
 *
 */
public class NationalId {

	private String Number;
 
    private String Type;
	 
	private String CountyOfIssue;
	  
	public NationalId() {
		
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getCountyOfIssue() {
		return CountyOfIssue;
	}

	public void setCountyOfIssue(String countyOfIssue) {
		CountyOfIssue = countyOfIssue;
	}

	@Override
	public String toString() {
		return "NationalId [Number=" + Number + ", Type=" + Type + ", CountyOfIssue=" + CountyOfIssue + "]";
	}

	
	

}
