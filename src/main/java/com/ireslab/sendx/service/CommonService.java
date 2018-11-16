package com.ireslab.sendx.service;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto;
import com.ireslab.sendx.electra.model.NotificationRequest;
import com.ireslab.sendx.electra.model.NotificationResponse;
import com.ireslab.sendx.electra.model.PaymentRequest;
import com.ireslab.sendx.electra.model.PaymentResponse;
import com.ireslab.sendx.electra.model.ProductRequest;
import com.ireslab.sendx.electra.model.ProductResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;
import com.ireslab.sendx.model.CheckoutBanksResponse;
import com.ireslab.sendx.model.ContactListVerificationRequest;
import com.ireslab.sendx.model.ContactListVerificationResponse;
import com.ireslab.sendx.model.CountryDetails;
import com.ireslab.sendx.model.CountryListResponse;
import com.ireslab.sendx.model.MiscConfigDetailsResponse;
import com.ireslab.sendx.model.UserProfile;


/**
 * @author Nitin
 *
 */
public interface CommonService {

	/**
	 * @param countryDialCode
	 * @return
	 */
	public CountryDetails getCountryDetails(String countryDialCode);

	/**
	 * @return
	 */
	public CountryListResponse getAllCountryDetails();

	/**
	 * @param contactListVerificationRequest
	 * @return
	 */
	public ContactListVerificationResponse validateContacts(
			ContactListVerificationRequest contactListVerificationRequest);

	/**
	 * @param countryDialCode
	 * @return
	 */
	public CheckoutBanksResponse getCheckoutBanksDetails(String countryDialCode);

	/**
	 * @param mobileNumber
	 * @param countryDialCode
	 * @return
	 */
	public MiscConfigDetailsResponse getMiscConfigDetails(BigInteger mobileNumber, String countryDialCode);

	public ProductResponse getProductList(ProductRequest productRequest);

	public PaymentResponse makePayment(PaymentRequest paymentRequest);

	public SendxElectraResponse updateDeviceSpecificParameter(SendxElectraRequest sendxElectraRequest);

	public PaymentResponse sendInvoicePayload(PaymentRequest paymentRequest);

	public SendxElectraResponse updateNotification(SendxElectraRequest sendxElectraRequest);

	public SendxElectraResponse getAllNotification(String mobileNumber);

	public UserProfile searchUserByuniqueCodeInElectra(String beneficiaryUniqueCode);

	public NotificationResponse deleteNotificationData(NotificationRequest notificationRequest);

	public PaymentResponse generateReceiptInvoice(PaymentRequest paymentRequest);

	public UserProfile searchUserByDialCodeAndMobileInElectra(String beneficiaryCountryDialCode,
			Long beneficiaryMobileNumber);

	public void downloadInvoicePdf(PaymentRequest paymentRequest, HttpServletRequest request,
			HttpServletResponse response);

	String generateInvoiceAndPdfString(ReceiptAndInvoiceUtilDto dto);

	
}
