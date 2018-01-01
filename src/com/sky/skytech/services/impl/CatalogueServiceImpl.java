package com.sky.skytech.services.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.sky.skytech.services.SkyService;
import com.sky.skytech.services.constants.SkyServiceConstants;
import com.sky.skytech.services.errors.SkyServiceException;
import com.sky.skytech.services.interfaces.CatalogueService;

public class CatalogueServiceImpl extends SkyService implements CatalogueService{

	// List of possible categories for locations
	HashMap<String, String> catagoryProducts = new HashMap<String, String>();

	public CatalogueServiceImpl() {
		// Set up catalogue products
		populateCatalogueProducts();
		
	}
	
	private void populateCatalogueProducts() {
		
		// A back-end database access would normally be done here
		
		catagoryProducts.clear();
		catagoryProducts.put(SkyServiceConstants.CATEGORY_SPORTS_ARSENAL, SkyServiceConstants.LOCATION_LONDON); 	
		catagoryProducts.put(SkyServiceConstants.CATEGORY_SPORTS_CHELSEA, SkyServiceConstants.LOCATION_LONDON); 	
		catagoryProducts.put(SkyServiceConstants.CATEGORY_SPORTS_LIVERPOOL, SkyServiceConstants.LOCATION_LIVERPOOL); 	
		catagoryProducts.put(SkyServiceConstants.CATEGORY_NEWS_SKY, SkyServiceConstants.LOCATION_ALL); 			
		catagoryProducts.put(SkyServiceConstants.CATEGORY_NEWS_SKY_SPORTS, SkyServiceConstants.LOCATION_ALL); 	

	}


	// Return a list of valid products for a valid location ID, or an exception if the location ID cannot be found
	public ArrayList<String> getProductsForLocationID(String locationID) throws SkyServiceException {
		ArrayList<String> products = new ArrayList<String> ();

		for (String productKey : catagoryProducts.keySet()) {
			
		    // System.out.println("Key = " + productKey + " - " + catagoryProducts.get(productKey));
		    
		    String location = catagoryProducts.get(productKey);
		    
		    if (location.equals(locationID) || location.equals(SkyServiceConstants.LOCATION_ALL)) {
		    	
		    	products.add(productKey);
		    }
		    		
		}		
		
		return products;
	}

}
