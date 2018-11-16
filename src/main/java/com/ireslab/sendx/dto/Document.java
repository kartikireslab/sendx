/**
 * 
 */
package com.ireslab.sendx.dto;

import java.util.Arrays;

/**
 * @author ireslab
 *
 */
public class Document {

	  private String DocumentFrontImage = null;

	  private String DocumentBackImage = null;

	  private byte[] LivePhoto = null;

	  private String DocumentType = null;
	  
	public Document() {
		
	}

	public String getDocumentFrontImage() {
		return DocumentFrontImage;
	}

	public void setDocumentFrontImage(String documentFrontImage) {
		DocumentFrontImage = documentFrontImage;
	}

	public String getDocumentBackImage() {
		return DocumentBackImage;
	}

	public void setDocumentBackImage(String documentBackImage) {
		DocumentBackImage = documentBackImage;
	}

	public byte[] getLivePhoto() {
		return LivePhoto;
	}

	public void setLivePhoto(byte[] livePhoto) {
		LivePhoto = livePhoto;
	}

	public String getDocumentType() {
		return DocumentType;
	}

	public void setDocumentType(String documentType) {
		DocumentType = documentType;
	}

	@Override
	public String toString() {
		return "Document [DocumentFrontImage=" + DocumentFrontImage + ", DocumentBackImage=" + DocumentBackImage
				+ ", LivePhoto=" + Arrays.toString(LivePhoto) + ", DocumentType=" + DocumentType + "]";
	}

	

}
