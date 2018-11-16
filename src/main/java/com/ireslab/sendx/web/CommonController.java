package com.ireslab.sendx.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.sendx.electra.model.PaymentRequest;
import com.ireslab.sendx.electra.model.PaymentResponse;
import com.ireslab.sendx.electra.model.ProductRequest;
import com.ireslab.sendx.electra.model.ProductResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;
import com.ireslab.sendx.service.CommonService;

@RestController

public class CommonController {
	
private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private ObjectWriter objectWriter;
	
	
	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping(value="products/{mobileNumber}",method=RequestMethod.POST)
	public ResponseEntity<ProductResponse> getProducetList(@RequestBody ProductRequest productRequest) throws JsonProcessingException{
		LOG.info("Product Request received : "+objectWriter.writeValueAsString(productRequest));
		ProductResponse productResponse = commonService.getProductList(productRequest);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="makePayment",method=RequestMethod.POST)
	public ResponseEntity<PaymentResponse> getProducetList(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.info("Make payment for Product Request received : "+objectWriter.writeValueAsString(paymentRequest));
	    PaymentResponse productResponse = commonService.makePayment(paymentRequest);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="generateReceiptInvoice",method=RequestMethod.POST)
	public ResponseEntity<PaymentResponse> generateReceiptInvoice(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.info("Request for generate recipt and invoice : "+objectWriter.writeValueAsString(paymentRequest));
	    PaymentResponse productResponse = commonService.generateReceiptInvoice(paymentRequest);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="updateDeviceSpecificParameter",method=RequestMethod.POST)
	public ResponseEntity<SendxElectraResponse> updateDeviceSpecificParameter(@RequestBody SendxElectraRequest sendxElectraRequest) throws JsonProcessingException{
		LOG.info("Device specific parameter update request received : "+objectWriter.writeValueAsString(sendxElectraRequest));
		SendxElectraResponse sendxElectraResponse = commonService.updateDeviceSpecificParameter(sendxElectraRequest);
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="sendInvoicePayload",method=RequestMethod.POST)
    public ResponseEntity<PaymentResponse> sendInvoicePayload(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.info("Make payment for Product Request received : "+objectWriter.writeValueAsString(paymentRequest));
	    PaymentResponse productResponse = commonService.sendInvoicePayload(paymentRequest);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="updateNotification",method=RequestMethod.POST)
    public ResponseEntity<SendxElectraResponse> updateNotification(@RequestBody SendxElectraRequest sendxElectraRequest) throws JsonProcessingException{
		LOG.info("Request received to delete Notification [notificationId:"+sendxElectraRequest.getNotificationId()+""+objectWriter.writeValueAsString(sendxElectraRequest));
		SendxElectraResponse sendxElectraResponse = commonService.updateNotification(sendxElectraRequest);
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="getAllNotification/{countryDailCode}/{mobileNumber}",method=RequestMethod.GET)
    public ResponseEntity<SendxElectraResponse> getAllNotification(@PathVariable("mobileNumber") String mobileNumber,@PathVariable("countryDailCode") String countryDailCode) throws JsonProcessingException{
		//LOG.info("Request received to delete Notification [notificationId:"+sendxElectraRequest.getNotificationId()+""+objectWriter.writeValueAsString(sendxElectraRequest));
		SendxElectraResponse sendxElectraResponse = commonService.getAllNotification(mobileNumber);
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}
	
	@RequestMapping(value="downloadInvoicePdf",method=RequestMethod.POST,  produces = "application/pdf")
	public void downloadInvoicePdf(@RequestBody PaymentRequest paymentRequest,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		LOG.info("Request for generate recipt and invoice : "+objectWriter.writeValueAsString(paymentRequest));
	    commonService.downloadInvoicePdf(paymentRequest, request, response);
		
	}
	
}
