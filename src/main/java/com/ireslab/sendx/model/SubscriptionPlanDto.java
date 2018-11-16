/**
 * 
 */
package com.ireslab.sendx.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ireslab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionPlanDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer subscriptionId;
	
	private String planTitle;
	
	private Integer token;
	
	private String validity;
	
	private Integer supportedUsers;
	
	public SubscriptionPlanDto() {
		
	}

	/**
	 * @return the subscriptionId
	 */
	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	/**
	 * @param subscriptionId the subscriptionId to set
	 */
	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the planTitle
	 */
	public String getPlanTitle() {
		return planTitle;
	}

	/**
	 * @param planTitle the planTitle to set
	 */
	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	/**
	 * @return the token
	 */
	public Integer getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(Integer token) {
		this.token = token;
	}

	/**
	 * @return the validity
	 */
	public String getValidity() {
		return validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(String validity) {
		this.validity = validity;
	}

	/**
	 * @return the supportedUsers
	 */
	public Integer getSupportedUsers() {
		return supportedUsers;
	}

	/**
	 * @param supportedUsers the supportedUsers to set
	 */
	public void setSupportedUsers(Integer supportedUsers) {
		this.supportedUsers = supportedUsers;
	}
	
	

}
