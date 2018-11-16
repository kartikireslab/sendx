package com.ireslab.sendx.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;

import com.google.gson.annotations.SerializedName;
//import com.trulioo.normalizedapi.model.Record;
//import com.trulioo.normalizedapi.model.ServiceError;
//import com.trulioo.normalizedapi.model.VerifyResult;

import io.swagger.annotations.ApiModelProperty;



public class VerifyResponse {
	 
	  private String TransactionID;

	 
	  private String UploadedDt;

	  
	  private String CountryCode;

	 
	  private String ProductName;

	
	  private Record Record;

	
	  private List<ServiceError> Errors;


	public String getTransactionID() {
		return TransactionID;
	}


	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}


	public String getUploadedDt() {
		return UploadedDt;
	}


	public void setUploadedDt(String uploadedDt) {
		UploadedDt = uploadedDt;
	}


	public String getCountryCode() {
		return CountryCode;
	}


	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}


	public String getProductName() {
		return ProductName;
	}


	public void setProductName(String productName) {
		ProductName = productName;
	}


	public Record getRecord() {
		return Record;
	}


	public void setRecord(Record record) {
		Record = record;
	}


	public List<ServiceError> getErrors() {
		return Errors;
	}


	public void setErrors(List<ServiceError> errors) {
		Errors = errors;
	}


	@Override
	public String toString() {
		return "VerifyResponse [TransactionID=" + TransactionID + ", UploadedDt=" + UploadedDt + ", CountryCode="
				+ CountryCode + ", ProductName=" + ProductName + ", Record=" + Record + ", Errors=" + Errors + "]";
	}

	 
}