package com.sky.skytech.services.errors;

public class SkyServiceCustNotFoundException extends SkyServiceException {

	public SkyServiceCustNotFoundException() {
	}

	public SkyServiceCustNotFoundException(String custID) {
		super("Customer "+custID+" not found in database.");
	}
}
