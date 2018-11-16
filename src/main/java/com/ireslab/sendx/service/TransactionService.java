package com.ireslab.sendx.service;

import java.math.BigInteger;

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

/**
 * @author Nitin
 *
 */
public interface TransactionService {

	/**
	 * @param sendTokensRequest
	 * @return
	 */
	public SendTokensResponse handleTokensTransfer(SendTokensRequest sendTokensRequest);

	/**
	 * @param cashOutRequest
	 * @return
	 */
	public CashOutResponse handleCashOutTokens(CashOutRequest cashOutRequest);

	/**
	 * @param txnHistoryRequest
	 * @return
	 */
	public TransactionHistoryResponse handleTransactionHistory(TransactionHistoryRequest txnHistoryRequest);
	
	/**
	 * @param loadtokensRequest
	 * @return
	 */
	public LoadTokensResponse handleLoadTokens(LoadTokensRequest loadtokensRequest);
	
	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public GenericResponse validateUserTopUp(BigInteger mobileNumber, String countryDialCode);

	public TransactionPurposeResponse getAllTransactionPurpose(String clientCorrelationId);
}
