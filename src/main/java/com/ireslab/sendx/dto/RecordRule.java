package com.ireslab.sendx.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModelProperty;

public class RecordRule {
	
	private String RuleName = null;

	
	private String Note = null;


	public String getRuleName() {
		return RuleName;
	}


	public void setRuleName(String ruleName) {
		RuleName = ruleName;
	}


	public String getNote() {
		return Note;
	}


	public void setNote(String note) {
		Note = note;
	}


	@Override
	public String toString() {
		return "RecordRule [RuleName=" + RuleName + ", Note=" + Note + "]";
	}

	

}
