package com.ireslab.sendx.web;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.sendx.electra.model.TransactionPurposeResponse;
import com.ireslab.sendx.model.CashOutRequest;
import com.ireslab.sendx.model.CashOutResponse;
import com.ireslab.sendx.model.GenericResponse;
import com.ireslab.sendx.model.LoadTokensRequest;
import com.ireslab.sendx.model.LoadTokensResponse;
import com.ireslab.sendx.model.SendTokensRequest;
import com.ireslab.sendx.model.SendTokensResponse;
import com.ireslab.sendx.model.TransactionHistoryRequest;
import com.ireslab.sendx.model.TransactionHistoryResponse;
import com.ireslab.sendx.service.TransactionService;
import com.ireslab.sendx.springsecurity.SpringSecurityUtil;

/**
 * @author Nitin
 *
 */
@RestController
public class TransactionController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private TransactionService transactionService;

	/**
	 * @param cashOutRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/loadTokens", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoadTokensResponse loadTokens(@RequestBody LoadTokensRequest loadTokensRequest)
			throws JsonProcessingException {

		LoadTokensResponse loadTokensResponse = null;

		nameRequestThread((loadTokensRequest.getCountryDialCode() + loadTokensRequest.getMobileNumber()),
				RequestType.LOAD_TOKENS, loadTokensRequest);

		loadTokensResponse = transactionService.handleLoadTokens(loadTokensRequest);
		LOG.debug("JSON Response - " + objectWriter.writeValueAsString(loadTokensResponse));

		return loadTokensResponse;
	}

	/**
	 * @param sendTokensRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/transferTokens", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public SendTokensResponse transferTokens(@RequestBody SendTokensRequest sendTokensRequest)
			throws JsonProcessingException {

		SendTokensResponse sendTokensResponse = null;

		nameRequestThread((sendTokensRequest.getSenderCountryDialCode() + sendTokensRequest.getSenderMobileNumber()),
				RequestType.TRANSFER_TOKENS, sendTokensRequest);

		sendTokensResponse = transactionService.handleTokensTransfer(sendTokensRequest);
		LOG.debug("Token transfer response sent - " + objectWriter.writeValueAsString(sendTokensResponse));

		return sendTokensResponse;
	}

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/validateUserTopUp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponse validateUserTopUp(@RequestParam(value = "mobileNumber", required = false) Long mobileNumber,
			@RequestParam(value = "countryDialCode", required = false) String countryDialCode)
			throws JsonProcessingException {

		GenericResponse validateUserTopUpResponse = null;

		if (mobileNumber == null || countryDialCode == null) {
			String[] usernameToken = SpringSecurityUtil.usernameFromSecurityContext();
			mobileNumber = new Long(usernameToken[1]);
			countryDialCode = usernameToken[0];
		}

		LOG.debug("Validate User TopUp request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		validateUserTopUpResponse = transactionService.validateUserTopUp(BigInteger.valueOf(mobileNumber),
				countryDialCode);
		LOG.debug("Request Activation code response sent - "
				+ objectWriter.writeValueAsString(validateUserTopUpResponse));

		return validateUserTopUpResponse;
	}

	/**
	 * @param cashOutRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/cashOut", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CashOutResponse cashOutTokens(@RequestBody CashOutRequest cashOutRequest) throws JsonProcessingException {

		CashOutResponse cashOutResponse = null;

		nameRequestThread((cashOutRequest.getCountryDialCode() + cashOutRequest.getMobileNumber()),
				RequestType.CASHOUT_TOKENS, cashOutRequest);

		cashOutResponse = transactionService.handleCashOutTokens(cashOutRequest);
		LOG.debug("CashOut Tokens response sent - " + objectWriter.writeValueAsString(cashOutResponse));

		return cashOutResponse;
	}

	/**
	 * @param cashOutRequest
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/transactionHistory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionHistoryResponse transactionHistory(@RequestBody TransactionHistoryRequest tnxHistoryRequest)
			throws JsonProcessingException {

		TransactionHistoryResponse tnxHistoryResponse = null;
		LOG.debug("Transaction History request received - " + objectWriter.writeValueAsString(tnxHistoryRequest));

		tnxHistoryResponse = transactionService.handleTransactionHistory(tnxHistoryRequest);
		LOG.debug("Transaction History response sent - " + objectWriter.writeValueAsString(tnxHistoryResponse));

		return tnxHistoryResponse;
	}
	
	
	@RequestMapping(value = "/getAllTransactionPurpose", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TransactionPurposeResponse getAllTransactionPurpose(@RequestParam(value = "clientCorrelationId", required = true) String clientCorrelationId)
			throws JsonProcessingException {

		TransactionPurposeResponse transactionPurposeResponse = null;
		LOG.debug("Transaction Purpose request received :- \n client correlation Id - " + clientCorrelationId );

		transactionPurposeResponse = transactionService.getAllTransactionPurpose(clientCorrelationId);
		LOG.debug("Transaction History response sent - " + objectWriter.writeValueAsString(transactionPurposeResponse));

		return transactionPurposeResponse;
	}
}
