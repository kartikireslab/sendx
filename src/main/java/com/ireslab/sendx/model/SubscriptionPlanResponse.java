/**
 * 
 */
package com.ireslab.sendx.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author ireslab
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionPlanResponse extends GenericResponse{

private static final long serialVersionUID = 1L;
	
	private List<SubscriptionPlanDto> subscriptionPlanDto;
	
	public SubscriptionPlanResponse() {
		
	}

	public List<SubscriptionPlanDto> getSubscriptionPlanDto() {
		return subscriptionPlanDto;
	}

	public void setSubscriptionPlanDto(List<SubscriptionPlanDto> subscriptionPlanDto) {
		this.subscriptionPlanDto = subscriptionPlanDto;
	}

}
