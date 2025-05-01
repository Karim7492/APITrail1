package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.address.AddAddress_Input_Pojo;
import com.omrbranch.pojo.address.AddAddress_Output_Pojo;
import com.omrbranch.pojo.address.SetAddress_Input_Pojo;
import com.omrbranch.pojo.address.SetAddress_Output_Pojo;
import com.omrbranch.pojo.cart.AddToCart_Input_Pojo;
import com.omrbranch.pojo.cart.AddToCart_Output_Pojo;
import com.omrbranch.pojo.cart.CartData;
import com.omrbranch.pojo.cart.CartDetails;
import com.omrbranch.pojo.cart.ClearCart_Output_Pojo;
import com.omrbranch.pojo.cart.GetCart_Output_Pojo;
import com.omrbranch.pojo.cart.GetSearchResult_Input_Pojo;
import com.omrbranch.pojo.cart.GetSearchResult_Output_Pojo;
import com.omrbranch.pojo.cart.ProductSearchData;
import com.omrbranch.pojo.cart.SearchProduct_Input_Pojo;
import com.omrbranch.pojo.cart.SearchProduct_Output_Pojo;
import com.omrbranch.pojo.cart.SearchResultData;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.pojo.order.CancelOrder_Input_Pojo;
import com.omrbranch.pojo.order.CancelOrder_Output_Pojo;
import com.omrbranch.pojo.order.CreateOrder_Input_Pojo;
import com.omrbranch.pojo.order.CreateOrder_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class APICategoryUsingSearchBtn extends BaseClass{
	String productVariationIdText;
	String productIdText;
	String categoryIdText;
	
	String cartIdText;
	String orderIdText;

	String first_name = "Abdul Karim";
	String last_name = "A";
	String mobile = "0123456789";
	String mobileUpdate = "1234567891";
	String apartment = "Aparts";
	int state = 35;
	int stateUpdate = 33;
	int city = 3685;
	int cityUpdate = 3378;
	int country = 101;
	String zipcode = "600032";
	String address = "1/2 Kuruku st Dubai";
	String address_type = "Aparts";
	String addressIdText;
	
	String logToken;
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
	public void clearCart() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		Response response = addRequest("GET", "https://omrbranch.com/api/clearCart");
		
		ClearCart_Output_Pojo clearCart_Output_Pojo = response.as(ClearCart_Output_Pojo.class);
		String clearCartMessage = clearCart_Output_Pojo.getMessage();
		System.out.println(clearCartMessage);
		

	}
	
	
	@Test(priority = 3)
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

	
	@Test(priority = 4)
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
	@Test(priority = 5)
	public void addToCart() {
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		Header h3 = new Header("Authorization", "Bearer " + logToken);

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		AddToCart_Input_Pojo addToCart_Input_Pojo = new AddToCart_Input_Pojo(productIdText, productVariationIdText, "plus");
		addPayload(addToCart_Input_Pojo);

		Response response = addRequest("POST", "https://omrbranch.com/api/addToCart");
		AddToCart_Output_Pojo addToCart_Output_Pojo = response.as(AddToCart_Output_Pojo.class);
		CartData cartData = addToCart_Output_Pojo.getData();
		System.out.println(cartData);
		String productAddedMesage = addToCart_Output_Pojo.getMessage();
		System.out.println(productAddedMesage);
	}

	@Test(priority = 6)
	public void getCartItems() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeader(headers);

		Response response = addRequest("GET", "https://omrbranch.com/api/getCartItems");
		GetCart_Output_Pojo getCart_Output_Pojo = response.as(GetCart_Output_Pojo.class);
		ArrayList<CartDetails> cartData = getCart_Output_Pojo.getData();

		System.out.println(cartData);
		for (CartDetails cartDat : cartData) {

			int cart_id = cartDat.getCart_id();
			cartIdText = String.valueOf(cart_id);
			System.out.println(cart_id);
		}

	}

	@Test(priority = 7)
	public void addUserAddress() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile,
				apartment, state, city, country, zipcode, address, address_type);

		addPayload(addAddress_Input_Pojo);
		Response response = addRequest("POST", "https://omrbranch.com/api/addUserAddress");
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		int address_id = addAddress_Output_Pojo.getAddress_id();
		addressIdText = String.valueOf(address_id);
		System.out.println("Address ID : " + addressIdText);

	}

	@Test(priority = 8)
	public void setAddress() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		SetAddress_Input_Pojo setAddress_Input_Pojo = new SetAddress_Input_Pojo(addressIdText, cartIdText);

		addPayload(setAddress_Input_Pojo);

		Response response = addRequest("POST", "https://omrbranch.com/api/setAddress");
		SetAddress_Output_Pojo setAddress_Output_Pojo = response.as(SetAddress_Output_Pojo.class);
		String setAddressMesage = setAddress_Output_Pojo.getMessage();
		System.out.println(setAddressMesage);
		assertEquals(setAddressMesage, "Cart updated successfully", "Verify Set Addres Message");
	}

	@Test(priority = 9)
	public void createOrder() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		CreateOrder_Input_Pojo createOrder_Input_Pojo = new CreateOrder_Input_Pojo("debit_card", "5555555555552222",
				"visa", "2025", "07", "234");
		addPayload(createOrder_Input_Pojo);

		Response response = addRequest("POST", "https://omrbranch.com/api/createOrder");
		CreateOrder_Output_Pojo createOrder_Output_Pojo = response.as(CreateOrder_Output_Pojo.class);

		int order_id = createOrder_Output_Pojo.getOrder_id();
		orderIdText = String.valueOf(order_id);
		System.out.println("Order ID : "+orderIdText);
	}

	@Test(priority = 10)
	public void cancelOrder() {

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logToken);
		Header h3 = new Header("Content-Type", "application/json");

		List<Header> listHeader = new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);

		Headers headers = new Headers(listHeader);
		addHeader(headers);

		CancelOrder_Input_Pojo cancelOrder_Input_Pojo = new CancelOrder_Input_Pojo(orderIdText);
		addPayload(cancelOrder_Input_Pojo);

		Response response = addRequest("POST", "https://omrbranch.com/api/cancelOrder");
		CancelOrder_Output_Pojo cancelOrder_Output_Pojo = response.as(CancelOrder_Output_Pojo.class);
		String cancelOrderMessage = cancelOrder_Output_Pojo.getMessage();
		System.out.println("Order Message : " + cancelOrderMessage);

		getCartItems();
	}
		
		
		
	}
	


