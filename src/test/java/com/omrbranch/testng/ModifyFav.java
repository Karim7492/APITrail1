package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.cart.GetSearchResult_Input_Pojo;
import com.omrbranch.pojo.cart.GetSearchResult_Output_Pojo;
import com.omrbranch.pojo.cart.ProductSearchData;
import com.omrbranch.pojo.cart.SearchProduct_Input_Pojo;
import com.omrbranch.pojo.cart.SearchProduct_Output_Pojo;
import com.omrbranch.pojo.cart.SearchResultData;
import com.omrbranch.pojo.fav.FavouiteData;
import com.omrbranch.pojo.fav.GetUserFavourites_Output_Found_Pojo;
import com.omrbranch.pojo.fav.GetUserFavourites_Output_NotFound_Pojo;
import com.omrbranch.pojo.fav.ManageUserFavourites_Input_Pojo;
import com.omrbranch.pojo.fav.ManageUserFavourites_Output_Pojo;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ModifyFav extends BaseClass {
	String logToken;
	String categoryIdText;
	String variationIdText;
	String productIdText;
	String cartIdText;
	String orderIdText;
String	productVariationIdText;
	
	@Test(priority = 1)
	public void login() {
		addHeader("accept", "application/json");

		addBasicAuth("abdulsensor@ymail.com", "Karim@123");

		Response response = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200, "Status Code");

		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response
				.as(PostmanBasicAuthLogin_Output_Pojo.class);

		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);

		logToken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
		System.out.println(logToken);
		assertEquals(first_name, "Abdul Karim", "Verify PostmanBasic Authetication First Name");

	}
	
	
	
	@Test(priority = 2)
	public void searchProduct() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		SearchProduct_Input_Pojo searchProduct_Input_Pojo = new SearchProduct_Input_Pojo("nuts");
		addPayload(searchProduct_Input_Pojo);
		
		Response response = addRequest("POST", "https://omrbranch.com/api/searchProduct");
		SearchProduct_Output_Pojo searchProduct_Output_Pojo = response.as(SearchProduct_Output_Pojo.class);
		ArrayList<ProductSearchData> searchProductsData = searchProduct_Output_Pojo.getData();
		
		for (ProductSearchData product : searchProductsData) {
			
			String productText = product.getText();
			if (productText.equals("Happilo 100% Natural Premium California Almonds | Premium Badam Giri in Fruit & Nuts")) {
				
				int product_id = product.getId();
				productIdText = String.valueOf(product_id);
				int category_id = product.getCategory_id();
				categoryIdText = String.valueOf(category_id);
				System.out.println("Product Id: "+product_id);
				System.out.println("Category Id :"+category_id);
				
			}
			
				
			}
			
		}
	@Test(priority = 3)
	public void getSearchResult() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		GetSearchResult_Input_Pojo getSearchResult_Input_Pojo = new GetSearchResult_Input_Pojo(categoryIdText, productIdText, "category");
		addPayload(getSearchResult_Input_Pojo);
		
		Response response = addRequest("POST", "https://omrbranch.com/api/getSearchResult");
		GetSearchResult_Output_Pojo getSearchResult_Output_Pojo = response.as(GetSearchResult_Output_Pojo.class);
		ArrayList<SearchResultData> searchResultsData = getSearchResult_Output_Pojo.getData();
		
		for (SearchResultData searchResultData : searchResultsData) {
			
			int product_variation_id = searchResultData.getProduct_variation_id();
			productVariationIdText = String.valueOf(product_variation_id);
			System.out.println(productVariationIdText);
			
		}
	}
	
	@Test(priority=4)
	public void manageUserFavourites() {
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		ManageUserFavourites_Input_Pojo manageUserFavourites_Input_Pojo = new ManageUserFavourites_Input_Pojo(productIdText, productVariationIdText);
		addPayload(manageUserFavourites_Input_Pojo);
		
		Response response = addRequest("POST", "https://omrbranch.com/api/manageUserFavourite");
		ManageUserFavourites_Output_Pojo manageUserFavourites_Output_Pojo = response.as(ManageUserFavourites_Output_Pojo.class);
		int statusCode = manageUserFavourites_Output_Pojo.getStatus();
		assertEquals(statusCode, 200,"Verifying Status Code");
		
//		String messageManageFavourites = manageUserFavourites_Output_Pojo.getMessage();
//		if (messageManageFavourites=="Product removed from your favorites") {
//			
//			assertEquals(messageManageFavourites, "Product removed from your favorites","Verifying Manage Favourites Message");
//		}
//		else {
//			assertEquals(messageManageFavourites, "Product added in your favorites","Verifying Manage Favourites Message");
//		}
		
		ArrayList<FavouiteData> favouritsData = manageUserFavourites_Output_Pojo.getData();
		for (FavouiteData favouiteData : favouritsData) {
			
			int manageProductId = favouiteData.getId();		
			String manageProductIdText = String.valueOf(manageProductId);
			assertEquals(manageProductIdText, productIdText,"Verifying Product Id");
			
			int product_variation_id = favouiteData.getProduct_variation_id();
			String manageProductVariationIdText = String.valueOf(product_variation_id);
			assertEquals(manageProductVariationIdText, productVariationIdText,"Verifying Product Variation Id");
		}

	}
	@Test(priority = 5)
	public void getUserFavourites() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		
		
		List<Header> listHeader= new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		
		
		Headers headers = new Headers(listHeader);
		
		addHeader(headers);
		
		Response response = addRequest("GET", "https://omrbranch.com/api/getUserFavourites");
		int statusCode = response.getStatusCode();
		if (statusCode==400) {
			GetUserFavourites_Output_NotFound_Pojo getUserFavourites_Output_Pojo = response.as(GetUserFavourites_Output_NotFound_Pojo.class);
			String getFavouritesMessage = getUserFavourites_Output_Pojo.getMessage();
			assertEquals(getFavouritesMessage, "No favorites found","Verifying Get User Favourites Message");
		}else if (statusCode==200) {
			GetUserFavourites_Output_Found_Pojo getUserFavourites_Output_Found_Pojo = response.as(GetUserFavourites_Output_Found_Pojo.class);
			String getFavouritesMessage = getUserFavourites_Output_Found_Pojo.getMessage();
			assertEquals(getFavouritesMessage, "Favorites list fetched successfully.","Verifying Get User Favourites Message");
			
			
		} 
		
		
		
	}

	
	
	
}
