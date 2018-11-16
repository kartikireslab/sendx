package com.ireslab.sendx.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

public class ServiceError {
	  
	  private String Code = null;

	  
	  private String Message = null;


	public String getCode() {
		return Code;
	}


	public void setCode(String code) {
		Code = code;
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	@Override
	public String toString() {
		return "ServiceError [Code=" + Code + ", Message=" + Message + "]";
	}

	  
}
