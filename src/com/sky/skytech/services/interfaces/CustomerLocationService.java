package com.sky.skytech.services.interfaces;

import com.sky.skytech.services.errors.SkyServiceException;

public abstract interface CustomerLocationService {

	// Return a valid location ID for a valid customer, or an exception if the customer ID cannot be found
	public String getLocationIDForCustomer(String customerID) throws SkyServiceException;

	
}
