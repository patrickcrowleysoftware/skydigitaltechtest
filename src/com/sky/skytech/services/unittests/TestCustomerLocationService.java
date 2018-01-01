package com.sky.skytech.services.unittests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sky.skytech.services.errors.SkyServiceCustNotFoundException;
import com.sky.skytech.services.errors.SkyServiceException;
import com.sky.skytech.services.impl.CatalogueServiceImpl;
import com.sky.skytech.services.impl.CustomerLocationServiceImpl;
import com.sky.skytech.services.constants.SkyServiceConstants;

class TestCustomerLocationService {

	@Test
	void testCustLocn() {

		// Find the location ID for the given customer ID
		CustomerLocationServiceImpl custLocnSvce = new CustomerLocationServiceImpl();
		
		String locationID = "";
		try {
			locationID = custLocnSvce.getLocationIDForCustomer("1100002");
			
		} catch (SkyServiceException se) {
	
		}

		if (!locationID.equals(SkyServiceConstants.LOCATION_LONDON))	{
	
			fail("Incorrect Location ID for given customer ID");
		}
		
	}

}
