package com.sky.skytech.services.impl;

import java.util.HashMap;

import com.sky.skytech.services.SkyService;
import com.sky.skytech.services.interfaces.CustomerLocationService;
import com.sky.skytech.services.constants.SkyServiceConstants;
import com.sky.skytech.services.errors.SkyServiceException;
import com.sky.skytech.services.errors.SkyServiceCustNotFoundException;

public class CustomerLocationServiceImpl extends SkyService implements CustomerLocationService{

	// List of possible locations for customers
	HashMap<String, String> customerLocations = new HashMap<String, String>();
	
	public CustomerLocationServiceImpl() {
		
		// Set up customer locations
		populateCustomerLocations();
		
	}
	
	private void populateCustomerLocations() {
		
		// A back-end database access would normally be done here
		
		customerLocations.clear();
		customerLocations.put("1100001", SkyServiceConstants.LOCATION_LONDON);
		customerLocations.put("1100002", SkyServiceConstants.LOCATION_LONDON);
		customerLocations.put("1100003", SkyServiceConstants.LOCATION_LIVERPOOL);
		customerLocations.put("1100004", SkyServiceConstants.LOCATION_LONDON);
		customerLocations.put("1100005", SkyServiceConstants.LOCATION_LIVERPOOL);
		customerLocations.put("1100006", SkyServiceConstants.LOCATION_LIVERPOOL);
		customerLocations.put("1100007", SkyServiceConstants.LOCATION_LONDON);
		customerLocations.put("1100008", SkyServiceConstants.LOCATION_LONDON);
	}

	// Return a valid location ID for a valid customer, or an exception if the customer ID cannot be found
	public String getLocationIDForCustomer(String customerID) throws SkyServiceException {
		String locationID = "";
		
		locationID = customerLocations.get(customerID);
			
		if (locationID == null) {
			// Customer ID must not exist in the database so throw an appropriate exception

			SkyServiceCustNotFoundException se = new SkyServiceCustNotFoundException (customerID);
			
			throw se;
		}
		
		return locationID;
	}

	
}
