package com.omrbranch.testng;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.omrbranch.baseclass.BaseClass;
import com.omrbranch.pojo.address.AddAddress_Input_Pojo;
import com.omrbranch.pojo.address.AddAddress_Output_Pojo;
import com.omrbranch.pojo.address.AddressList;
import com.omrbranch.pojo.address.CityList;
import com.omrbranch.pojo.address.CityList_Input_Pojo;
import com.omrbranch.pojo.address.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.address.DeleteAddress_Output_Pojo;
import com.omrbranch.pojo.address.GetAddress_Output_Pojo;
import com.omrbranch.pojo.address.GetCityNameList_Output_Pojo;
import com.omrbranch.pojo.address.GetStateName_Output_Pojo;
import com.omrbranch.pojo.address.StateList;
import com.omrbranch.pojo.address.UpdateAddress_Input_Pojo;
import com.omrbranch.pojo.address.UpdateAddress_Output_Pojo;
import com.omrbranch.pojo.login.PostmanBasicAuthLogin_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class LoginBasicAuth extends BaseClass {
	
	String stateIdText;
	String logToken;
	String addressIdText;
	
	String first_name="Abdulll";
    String last_name="Az";
    String mobile="0123456789";
    String mobileUpdate="1234567891";
    String apartment="Aparts";
    int state=35;
    int stateUpdate=33;
    int city=3685;
    int cityUpdate=3378;
    int country=101;
    String zipcode="600032";
    String address="1/2 Kuruku st Dubai";
    String address_type="Aparts";
	
	@Test(priority = 1)
	public void login() {
		addHeader("accept", "application/json");
		
		addBasicAuth("abdulsensor@ymail.com", "Karim@123");
		
		Response response = addRequest("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200,"Status Code");
		
		PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response.as(PostmanBasicAuthLogin_Output_Pojo.class);
		
		String first_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		
		logToken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
		System.out.println(logToken);
		assertEquals(first_name, "Abdul Karim","Verify PostmanBasic Authetication First Name");
		
		

	}
	
	@Test(priority = 2)
	public void stateList() {
		//1.Header
		addHeader("accept", "application/json");
		
		Response response = addRequest("GET", "https://omrbranch.com/api/stateList");
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200,"Status Code");
		
//		GetStateName_Output_Pojo g= new GetStateName_Output_Pojo();
		GetStateName_Output_Pojo getStateName_Output_Pojo = response.as(GetStateName_Output_Pojo.class);
		
		ArrayList<StateList> stateList = getStateName_Output_Pojo.getData();
		for (StateList eachState : stateList) {
			String stateName = eachState.getName();
			if (stateName.equals("Tamil Nadu")) {
				
				int stateId = eachState.getId();
				stateIdText = String.valueOf(stateId);
				System.out.println(stateIdText);
				break;
				
			}
		
		
		}
		
		
		

	}
	@Test(priority = 3)
	public void getCityList() {
		
		List<Header> listHeader= new ArrayList<Header>();
		Header h1= new Header("accept","application/json");
		Header h2= new Header("Content-Type","application/json");
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		//1.Header
		addHeader(headers);
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(stateIdText);
		addPayload(cityList_Input_Pojo);
		Response response = addRequest("POST", "https://omrbranch.com/api/cityList");
		System.out.println(response.asPrettyString());
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		assertEquals(statusCode, 200,"Status Code");
		
		GetCityNameList_Output_Pojo getCityNameList_Output_Pojo= response.as(GetCityNameList_Output_Pojo.class);
		ArrayList<CityList> cityList = getCityNameList_Output_Pojo.getData();
		for (CityList eachCity : cityList) {
			
			String cityName = eachCity.getName();
			if (cityName.equals("Courtalam")) {
				
				int cityIdNum = eachCity.getId();
				System.out.println(cityIdNum);
				break;
			}
			
		}
		
		
	}
	@Test(priority = 4)
	public void addUserAddress() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		
		List<Header> listHeader= new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		
		Headers headers = new Headers(listHeader);
		addHeader(headers);
		
		AddAddress_Input_Pojo addAddress_Input_Pojo = new AddAddress_Input_Pojo(first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
		
		addPayload(addAddress_Input_Pojo);
		Response response = addRequest("POST", "https://omrbranch.com/api/addUserAddress");
		AddAddress_Output_Pojo addAddress_Output_Pojo = response.as(AddAddress_Output_Pojo.class);
		int address_id = addAddress_Output_Pojo.getAddress_id();
		addressIdText = String.valueOf(address_id); 
		System.out.println("Address ID : "+addressIdText);
		
	}
	
	
	@Test(priority = 5)
	public void getUserAddress() {
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		List<Header> listHeader= new ArrayList<Header>();
		listHeader.add(h1);
		listHeader.add(h2);
		Headers headers = new Headers(listHeader);
		addHeader(headers);
		Response response = addRequest("GET", "https://omrbranch.com/api/getUserAddress");
		GetAddress_Output_Pojo getAddress_Output_Pojo = response.as(GetAddress_Output_Pojo.class);
		ArrayList<AddressList> addressList = getAddress_Output_Pojo.getData();
		for (AddressList eachAddress : addressList) {
			int addressId = eachAddress.getId();
			String addressIdTextGet = String.valueOf(addressId);
			if (addressIdTextGet.equals(addressIdText)) {
				String addressGet = eachAddress.getAddress();
				String first_nameGet = eachAddress.getFirst_name();
				String last_nameGet = eachAddress.getLast_name();
				String mobileGet = eachAddress.getMobile();
				String apartmentGet = eachAddress.getApartment();
				int state_idGet = eachAddress.getState_id();
				int city_idGet = eachAddress.getCity_id();
				int country_idGet = eachAddress.getCountry_id();
				String zipcodeGet = eachAddress.getZipcode();
				String address_typeGet = eachAddress.getAddress_type();
				
				assertEquals(addressGet, address,"Verifying added address");
				assertEquals(first_nameGet, first_name,"Verifying added address");
				assertEquals(last_nameGet, last_name,"Verifying added address");
				assertEquals(mobileGet, mobile,"Verifying added address");
				assertEquals(apartmentGet, apartment,"Verifying added address");
				assertEquals(state_idGet, state,"Verifying added address");
				assertEquals(city_idGet, city,"Verifying added address");
				assertEquals(country_idGet, country,"Verifying added address");
				assertEquals(zipcodeGet, zipcode,"Verifying added address");
				assertEquals(address_typeGet, address_type,"Verifying added address");
				
			}
		}
		
	}
	
	@Test(priority = 6)
	public void updateAddress() {
		
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer "+logToken);
		Header h3 = new Header("Content-Type", "application/json");
		
		List<Header> listHeader= new ArrayList<Header>();
		
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		
		Headers headers = new Headers(listHeader);
		
		addHeader(headers);
		
		UpdateAddress_Input_Pojo updateAddress_Input_Pojo = new UpdateAddress_Input_Pojo(addressIdText, first_name, last_name, mobileUpdate, apartment, stateUpdate, cityUpdate, country, zipcode, address, address_type);
		
		addPayload(updateAddress_Input_Pojo);
		
		Response response = addRequest("PUT", "https://omrbranch.com/api/updateUserAddress");
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo = response.as(UpdateAddress_Output_Pojo.class);
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200,"Verify status code");
		String updateAddressMessage = updateAddress_Output_Pojo.getMessage();
		assertEquals(updateAddressMessage, "Address updated successfully","Verify Address updated Message");
		
	}
	
	
	@Test(priority = 7)
	public void deleteAdddress() {

		Header h1= new Header("accept", "application/json");
		Header h2= new Header("Authorization", "Bearer "+logToken);
		Header h3= new Header("Content-Type", "application/json");
		
		
		List<Header> listHeader= new ArrayList<Header>();
		
		listHeader.add(h1);
		listHeader.add(h2);
		listHeader.add(h3);
		
		Headers headers = new Headers(listHeader);
		
		addHeader(headers);
		
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(addressIdText);
		addPayload(deleteAddress_Input_Pojo);
		
		
		Response response = addRequest("DELETE", "https://omrbranch.com/api/deleteAddress");
		
		
		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		int statusCode = response.getStatusCode();
		assertEquals(statusCode, 200,"Verify Status code");
		String deleteAddressMessage = deleteAddress_Output_Pojo.getMessage();
		assertEquals(deleteAddressMessage, "Address deleted successfully","Verify Address Deleted Message");
		
		
		
		getUserAddress();
		
		
		
	}
	

}
