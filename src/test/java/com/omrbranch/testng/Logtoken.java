package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Logtoken extends BaseClass {
	
	RequestSpecification reqSpec;
	int stateId;
	int cityId;
	String logToken;
	int addressId;
	
	String firstName;
	String lastName;
	Object mobileNo;
	Object countryId;
	int stateIdAdd;
	int cityIdAdd;

	@Test(priority = 1)
	public void login() {
		//1.Header
		addHeader("accept", "application/json");
		//2.Basic Authentication from Authorization in POSTMAN
		addBasicAuth("abdulsensor@ymail.com", "Karim@123");
		//3.Add req type, endpoint
//		reqSpec.auth().preemptive().oauth2("");
		Response response = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200,"Status Code");
//		String resBodyAsPrettyString = getResBodyAsPrettyString(response);
//		System.out.println(resBodyAsPrettyString);
		JsonPath jsonPath = response.getBody().jsonPath();
		logToken = jsonPath.getString("data.logtoken");
		System.out.println("LogToken is :"+logToken);
	}
	@Test(priority = 2)
	public void getState() {
		
		addHeader("accept", "application/json");
		Response response = addRequest("GET", "https://omrbranch.com/api/stateList");
		
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200,"Status code verification");
		
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
		
		JsonPath jsonPath = response.getBody().jsonPath();
		List<String> stateNames = jsonPath.get("data.name");
for (int i = 0; i < stateNames.size(); i++) {
	if (stateNames.get(i).equals("Tamil Nadu")) {
		stateId = jsonPath.get("data["+i+"].id");
		System.out.println("State id : "+ stateId);
		
		
	}
}			
			
		System.out.println(stateNames);
		

	}
	@Test(priority = 3)
	public void getCity() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		
		
		List<Header> listHeaders = new ArrayList<Header>();
		
		listHeaders.add(h1);
		listHeaders.add(h2);
		
		Headers headers = new Headers(listHeaders);
		addHeader(headers);
		
		String payLoad="{ "
				+ "  \"state_id\": \"35\" "
				+ "}";
		
		addPayload(payLoad);
		Response response = addRequest("POST", "https://omrbranch.com/api/cityList");
		int statusCode = getStatusCode(response);
		assertEquals(statusCode, 200,"Status code verififcation");
		
		JsonPath jsonPath = response.getBody().jsonPath();
		
		List<String> cityNames = jsonPath.get("data.name");
		
		for (int i = 0; i < cityNames.size(); i++) {
			
			if (cityNames.get(i).equals("Chennai")) {
				cityId = jsonPath.get("data["+i+"].id");
				
				
			}
			
		}
		System.out.println(cityId);
		
}
	@Test(priority = 4)
	public void addAddress() {
		
		Header h1= new Header("accept", "application/json");
		Header h2= new Header("Authorization", "Bearer "+logToken);
		Header h3= new Header("Content-Type", "application/json");
		
		List<Header> listHeaders = new ArrayList<Header>();
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		
		Headers headers = new Headers(listHeaders);
		addHeader(headers);
		
		firstName="Karim";
		lastName="A";
		mobileNo="1234567898";
		countryId="101";
		
		String payLoad="{\r\n"
				+ "  \"first_name\": \""+firstName+"\",\r\n"
				+ "  \"last_name\": \""+lastName+"\",\r\n"
				+ "  \"mobile\": \""+mobileNo+"\",\r\n"
				+ "  \"apartment\": \"apartment\",\r\n"
				+ "  \"state\": "+stateId+",\r\n"
				+ "  \"city\": "+cityId+",\r\n"
				+ "  \"country\": "+countryId+",\r\n"
				+ "  \"zipcode\": \"202020\",\r\n"
				+ "  \"address\": \"64/63 partap nagar\",\r\n"
				+ "  \"address_type\": \"home\"\r\n"
				+ "}";
		System.out.println(payLoad);
		addPayload(payLoad);
		
		Response response = addRequest("POST", "https://omrbranch.com/api/addUserAddress");
		
		int statusCode = getStatusCode(response);
		assertEquals(statusCode, 200,"Status verify");
		
		JsonPath jsonPath = response.getBody().jsonPath();
		addressId = jsonPath.get("address_id");
		System.out.println("Address Id :"+addressId);
		}
	
	
	@Test(priority = 5)
	public void getAddress() {
			
		Header h1= new Header("accept", "application/json");
		Header h2= new Header("Authorization", "Bearer "+logToken);
		
		List<Header> listHeader= new ArrayList<Header>();
		
		listHeader.add(h1);
		listHeader.add(h2);
		
		Headers headers = new Headers(listHeader);
		
		addHeader(headers);
		
		Response response = addRequest("GET", "https://omrbranch.com/api/getUserAddress");
		
		JsonPath jsonPath = response.getBody().jsonPath();
		
		List<Object> addresses = jsonPath.get("data");
		for (int i = 0; i < addresses.size(); i++) {
			
			
			Map<String, Object> address = (Map<String, Object>) addresses.get(i);
			if (address.get("id").equals(addressId)) {
				
				System.out.println(address);
				Object firstNameGet = address.get("first_name");
				Object lastNameGet = address.get("last_name");
				Object mobileNoGet = address.get("mobile");
				String countryIdGet = address.get("country_id").toString();
				Object stateIdGet = address.get("state_id");
				Object cityIdGet = address.get("city_id");
				
				assertEquals(stateIdGet, stateId,"Verify added State");
				assertEquals(cityIdGet, cityId,"Verfiy added City");
				assertEquals(firstNameGet, firstName,"Verfiy added firstName");
				assertEquals(lastNameGet, lastName,"Verfiy added lastName");
				assertEquals(mobileNoGet, mobileNo,"Verfiy added mobile No");
				assertEquals(countryIdGet, countryId,"Verfiy added Country Id");
				
			}
			
			
//			System.out.println(address.get("id"));
			
		}
//		System.out.println(addresses);
		
		
		
		
	}	
		
		
		
		
	
	
	@Test(priority = 6)
	public void modfiyAddress() {
     
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		
		List<Header> listHeader= new ArrayList<Header>();
		
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		
		Headers headers = new Headers(listHeader);
		
		addHeader(headers);
		
		String payLoad="{\r\n"
				+ "  \"address_id\": "+addressId+",\r\n"
				+ "  \"first_name\": \"Abdul\",\r\n"
				+ "  \"last_name\": \"Karim\",\r\n"
				+ "  \"mobile\": \"1234567898\",\r\n"
				+ "  \"apartment\": \"apartment\",\r\n"
				+ "  \"state\": 33,\r\n"
				+ "  \"city\": 3378,\r\n"
				+ "  \"country\": 101,\r\n"
				+ "  \"zipcode\": \"600059\",\r\n"
				+ "  \"address\": \"1/2 Welcome Nagar\",\r\n"
				+ "  \"address_type\": \"Office\"\r\n"
				+ "}";
		
		addPayload(payLoad);
		
		Response response = addRequest("PUT", "https://omrbranch.com/api/updateUserAddress");
		JsonPath jsonPath = response.getBody().jsonPath();
		int statusCode = getStatusCode(response);
		assertEquals(statusCode,200,"Verify Status Code");
		String updateAddress = jsonPath.get("message");
		assertEquals(updateAddress, "Address updated successfully","Verify Address Updated");
		
		
		
		
	}
	@Test(priority = 7)
	public void deleteAddress() {
		
		Header h1= new Header("accept", "application/json");
		Header h2= new Header("Authorization", "Bearer "+logToken);
		Header h3= new Header("Content-Type", "application/json");
		
		List<Header> listHeaders= new ArrayList<Header>();
		
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		
		Headers headers = new Headers(listHeaders);
		addHeader(headers);
		
		String payLoad= "{  "
				+ "  \"address_id\": "+addressId+"  "
				+ "}";
		addPayload(payLoad);
		
		Response response = addRequest("DELETE", "https://omrbranch.com/api/deleteAddress");
		
		JsonPath jsonPath = response.getBody().jsonPath();
		
		int statusCode = getStatusCode(response);
		assertEquals(statusCode, 200,"Status Verify Message");
		
		String deleteMessage = jsonPath.get("message");
		System.out.println(deleteMessage);
		
		assertEquals(deleteMessage, "Address deleted successfully","Verify Address Deleted");
		
	}
	
}
