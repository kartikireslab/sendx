package com.ireslab.sendx.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

public class DatasourceField {
	 
	  private String FieldName = null;

	
	  private String Status = null;

	  
	  private String FieldGroup = null;


	public String getFieldName() {
		return FieldName;
	}


	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public String getFieldGroup() {
		return FieldGroup;
	}


	public void setFieldGroup(String fieldGroup) {
		FieldGroup = fieldGroup;
	}


	@Override
	public String toString() {
		return "DatasourceField [FieldName=" + FieldName + ", Status=" + Status + ", FieldGroup=" + FieldGroup + "]";
	}

	  
}
