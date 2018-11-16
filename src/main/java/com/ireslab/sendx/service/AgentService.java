/**
 * 
 */
package com.ireslab.sendx.service;

import com.ireslab.sendx.model.AgentRequest;
import com.ireslab.sendx.model.AgentRequestBody;
import com.ireslab.sendx.model.AgentResponse;

public interface AgentService {

	public AgentResponse registerAgent(AgentRequest agentRequest);

	public AgentResponse getAgent(AgentRequestBody agentRequestBody);

}
