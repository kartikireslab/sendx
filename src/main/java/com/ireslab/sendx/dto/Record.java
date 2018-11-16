package com.ireslab.sendx.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

public class Record {
	 
	  private String TransactionRecordID = null;

	 
	  private String RecordStatus = null;

	 
	  private List<DatasourceResult> DatasourceResults = null;

	 
	  private List<ServiceError> Errors = null;

	
	  private RecordRule Rule = null;


	public String getTransactionRecordID() {
		return TransactionRecordID;
	}


	public void setTransactionRecordID(String transactionRecordID) {
		TransactionRecordID = transactionRecordID;
	}


	public String getRecordStatus() {
		return RecordStatus;
	}


	public void setRecordStatus(String recordStatus) {
		RecordStatus = recordStatus;
	}


	public List<DatasourceResult> getDatasourceResults() {
		return DatasourceResults;
	}


	public void setDatasourceResults(List<DatasourceResult> datasourceResults) {
		DatasourceResults = datasourceResults;
	}


	public List<ServiceError> getErrors() {
		return Errors;
	}


	public void setErrors(List<ServiceError> errors) {
		Errors = errors;
	}


	public RecordRule getRule() {
		return Rule;
	}


	public void setRule(RecordRule rule) {
		Rule = rule;
	}


	@Override
	public String toString() {
		return "Record [TransactionRecordID=" + TransactionRecordID + ", RecordStatus=" + RecordStatus
				+ ", DatasourceResults=" + DatasourceResults + ", Errors=" + Errors + ", Rule=" + Rule + "]";
	}

	 
}