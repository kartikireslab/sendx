package com.ireslab.sendx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.ireslab.sendx.electra.ElectraApiConfig;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

@Service
public class TestServiceImpl {
	
	private static final String FORMAT_SPECIFIER = "%s";
	@Autowired
	private TransactionalApiServiceImpl transactionalApiServiceImpl;
	
	@Autowired
	private ElectraApiConfig electraApiConfig;
	
	
	public SendxElectraResponse getAllTransactionalDetails(SendxElectraRequest sendxElectraRequest) {

		String exchangeEndPointUrl = String.format(electraApiConfig.getTransactionalDetailsApiEndpointUrl(),
				sendxElectraRequest.getUserCorrelationId(), FORMAT_SPECIFIER);
        
		//System.out.println("exchangeEndPointUrl :"+exchangeEndPointUrl);

		SendxElectraResponse sendxElectraResponse =(SendxElectraResponse) transactionalApiServiceImpl.invokeApi(exchangeEndPointUrl, HttpMethod.POST,
				SendxElectraResponse.class, sendxElectraRequest, false, false,false);

		//System.out.println(sendxElectraResponse.getTransactionDetailsDtos().size());
		return sendxElectraResponse;
	}
	
	

}
