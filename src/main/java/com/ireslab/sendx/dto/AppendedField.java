package com.ireslab.sendx.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

public class AppendedField {
	  
	  private String FieldName = null;

	  
	  private String Data = null;


	public String getFieldName() {
		return FieldName;
	}


	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}


	public String getData() {
		return Data;
	}


	public void setData(String data) {
		Data = data;
	}


	@Override
	public String toString() {
		return "AppendedField [FieldName=" + FieldName + ", Data=" + Data + "]";
	}

	  

}