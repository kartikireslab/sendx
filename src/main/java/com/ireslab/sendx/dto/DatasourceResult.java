package com.ireslab.sendx.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

public class DatasourceResult {
	  
	  private String DatasourceStatus = null;

	  
	  private String DatasourceName = null;

	  
	  private List<DatasourceField> DatasourceFields = null;

	  
	  private List<AppendedField> AppendedFields = null;

	 
	  private List<ServiceError> Errors = null;

	 
	  private List<String> FieldGroups = null;


	public String getDatasourceStatus() {
		return DatasourceStatus;
	}


	public void setDatasourceStatus(String datasourceStatus) {
		DatasourceStatus = datasourceStatus;
	}


	public String getDatasourceName() {
		return DatasourceName;
	}


	public void setDatasourceName(String datasourceName) {
		DatasourceName = datasourceName;
	}


	public List<DatasourceField> getDatasourceFields() {
		return DatasourceFields;
	}


	public void setDatasourceFields(List<DatasourceField> datasourceFields) {
		DatasourceFields = datasourceFields;
	}


	public List<AppendedField> getAppendedFields() {
		return AppendedFields;
	}


	public void setAppendedFields(List<AppendedField> appendedFields) {
		AppendedFields = appendedFields;
	}


	public List<ServiceError> getErrors() {
		return Errors;
	}


	public void setErrors(List<ServiceError> errors) {
		Errors = errors;
	}


	public List<String> getFieldGroups() {
		return FieldGroups;
	}


	public void setFieldGroups(List<String> fieldGroups) {
		FieldGroups = fieldGroups;
	}


	@Override
	public String toString() {
		return "DatasourceResult [DatasourceStatus=" + DatasourceStatus + ", DatasourceName=" + DatasourceName
				+ ", DatasourceFields=" + DatasourceFields + ", AppendedFields=" + AppendedFields + ", Errors=" + Errors
				+ ", FieldGroups=" + FieldGroups + "]";
	}

	  
}