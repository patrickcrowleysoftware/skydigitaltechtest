package com.sky.skytech.services.interfaces;

import java.util.ArrayList;

import com.sky.skytech.services.errors.SkyServiceException;

public abstract interface CatalogueService {

	// Return a list of valid products for a valid location ID, or an exception if the location ID cannot be found
	public ArrayList<String> getProductsForLocationID(String locationID) throws SkyServiceException;

}
