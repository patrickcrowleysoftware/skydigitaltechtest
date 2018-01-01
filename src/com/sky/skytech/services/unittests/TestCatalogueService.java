package com.sky.skytech.services.unittests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.sky.skytech.services.constants.SkyServiceConstants;
import com.sky.skytech.services.errors.SkyServiceException;
import com.sky.skytech.services.impl.CatalogueServiceImpl;
import com.sky.skytech.services.impl.CustomerLocationServiceImpl;

class TestCatalogueService {

	@Test
	void testCatalogue() {

		// Find the products for the given location ID
		CatalogueServiceImpl catSvce = new CatalogueServiceImpl();
		
		ArrayList<String> possibleProducts = null;
		try {
			possibleProducts = catSvce.getProductsForLocationID(SkyServiceConstants.LOCATION_LONDON);
			
		} catch (SkyServiceException se) {
			// No expected exceptions yet
		
		}


		if (possibleProducts.contains(SkyServiceConstants.CATEGORY_SPORTS_LIVERPOOL))	{
	
			fail("Incorrect product for given Location ID");
		}
		

	}

}
