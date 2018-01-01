package com.sky.skytech;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sky.skytech.services.constants.SkyServiceConstants;
import com.sky.skytech.services.errors.SkyServiceCustNotFoundException;
import com.sky.skytech.services.errors.SkyServiceException;
import com.sky.skytech.services.impl.CustomerLocationServiceImpl;
import com.sky.skytech.services.impl.CatalogueServiceImpl;


/**
 * Servlet implementation class SkyTechServlet
 */
@WebServlet("/SkyTechServlet")
public class SkyTechServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SkyTechServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Store the selected customers products in an array
		ArrayList<String> selectedProducts = new ArrayList<String>();

		checkProductSelected(request, SkyServiceConstants.CATEGORY_NEWS_SKY, selectedProducts);
		checkProductSelected(request, SkyServiceConstants.CATEGORY_NEWS_SKY_SPORTS, selectedProducts);
		checkProductSelected(request, SkyServiceConstants.CATEGORY_SPORTS_ARSENAL, selectedProducts);
		checkProductSelected(request, SkyServiceConstants.CATEGORY_SPORTS_CHELSEA, selectedProducts);
		checkProductSelected(request, SkyServiceConstants.CATEGORY_SPORTS_LIVERPOOL, selectedProducts);
		
		if (selectedProducts.size() == 0) {
			// No products were selected
			String resultHTML = formErrorResultHTML("No products were selected, please try again");
			
			// Build the response page
			generatePageOutput(resultHTML, request, response);

			
		} else {
			// Some products were selected
			
			// Next, get the hidden page value simulating a customer ID cookie
			String custID = request.getParameter("custID");
			
			if (custID == null || custID.equals("")) {
				// No customer ID provided
				
				System.out.println("NOTHING TO DO");
	
				String resultHTML = formErrorResultHTML("No customer ID provided, please try again");
				
				// Build the response page
				generatePageOutput(resultHTML, request, response);
	
				
			} else {
				// Customer ID provided, so now validate/confirm their choices
				
				System.out.println("CUSTOMER ID: "+custID);
		
				String resultText = validateSelections(custID, selectedProducts); 
				
				String resultHTML = formOKResultHTML(request, resultText);
				
				// Build the response page
				generatePageOutput(resultHTML, request, response);
	
			}
		}	
	}

	private String validateSelections(String customerID, ArrayList<String> selectedProducts) {
		String resultText = "";
		
		// Find the location ID for the given customer ID
		CustomerLocationServiceImpl custLocnSvce = new CustomerLocationServiceImpl();
		
		String locationID = "";
		
		try {
			locationID = custLocnSvce.getLocationIDForCustomer(customerID);
			
		} catch (SkyServiceException se) {
	
			if (se instanceof SkyServiceCustNotFoundException) {
				// Customer not found	
				resultText = "Cannot proceed since customer ID "+ customerID + " cannot be found";
				return resultText;
			}
		}
		
		resultText = "<h3><font=\"green\">Customer ID: " + customerID + " Location: "+locationID +"</font></h3><b/>\n";
		
		// Find the products for the given location ID
		CatalogueServiceImpl catSvce = new CatalogueServiceImpl();
		
		ArrayList<String> possibleProducts = null;
		try {
			possibleProducts = catSvce.getProductsForLocationID(locationID);
			
		} catch (SkyServiceException se) {
			// No expected exceptions yet
		
		}
		
		resultText += checkValidProductsForLocation(possibleProducts, selectedProducts);
		
		return resultText;
	}
	
	private String checkValidProductsForLocation(ArrayList<String> possibleProducts, ArrayList<String> selectedProducts) {
		String resultText = "";
		ArrayList<String> allowedProducts = new ArrayList<String>();
		ArrayList<String> disallowedProducts = new ArrayList<String>();
		
		boolean allowed=false;
		boolean disallowed=false;
		
		for (String product : selectedProducts) {
			
			if (!possibleProducts.contains(product)) {
				disallowed=true;
				disallowedProducts.add(product);
			} else {
				allowed=true;
				allowedProducts.add(product);
			}
		}
		
		if (allowed) {
			resultText += "<h4>You have selected Products:</h4><b />\n";
			resultText += "<h5>"+allowedProducts.toString()+"</h5><b />\n";
		}
		
		if (disallowed) {
			resultText += "<h4>These selected Products are not available in your area:</h4><b />\n";
			resultText += "<h5>"+disallowedProducts.toString()+"</h5><b />\n";
		}
		
		return resultText;	
	}
	
	private void checkProductSelected(HttpServletRequest request, String product, ArrayList<String> selectedProducts) {
		
		String param = request.getParameter(product);
		
		if (param == null || param.equals("")) {
			System.out.println(product+" is not specified");
		
		
		} else if (param.equalsIgnoreCase("ON")){
			System.out.println(product+" is specified");

			// Product is selected, so add it to the list of selected products
			selectedProducts.add(product);
		} else {
			
		}
			
	}
	
	
	private void generatePageOutput(String resultHTML, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
	      response.setContentType("text/html");

	      PrintWriter out = response.getWriter();

		  out.append("Pat Crowleys servlet, served at: ").append(request.getContextPath());

		  String title = "Sky Product Confirmation";
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	         
	      out.println(docType +
	 	         "<html>\n" +
	 	            "<head><title>" + title + "</title></head>\n" +
	 	            "<body bgcolor = \"#f0f0f0\">\n" +
	 	               "<h1 align = \"center\"><font color=\"darkblue\">" + title + "</font></h1>\n" +
	               	  "<div align=\"center\">" + 
	         	 	   
					resultHTML +
	 		 	          
	 		 	          "<h3><a href=\"http://localhost:8080/SkyTech/SkyProdSelection.html\">Select Products Again</a></h3>" +
	 	                  "</div>" + 
	 	          "</body></html>"
	 	      );
	 		
		
	}

	private String formErrorResultHTML(String errorText) {
		String outputText="";
		
		outputText = "<h2><b><font color=\"red\">" + errorText + "</font></b></h2>";
				
		return outputText;
	}

	private String formOKResultHTML(HttpServletRequest request, String result) {
		String outputText="";
		
		outputText = "<h2><b>" + result + "</b></h2>" +  
                  		"<br />";
		
		return outputText;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
}
