package com.omrbranch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omrbranch.pojo.Datum;
import com.omrbranch.pojo.Employee;
import com.omrbranch.pojo.Support;

public class ListFlightObjectMapper {
public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
	
	File file = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\ListFlight.json");
	ObjectMapper objectMapper= new ObjectMapper();
	Employee emp = objectMapper.readValue(file, Employee.class);
	int page = emp.getPage();
	System.out.println(page);
	int perPage = emp.getPer_page();
	System.out.println(perPage);
	int total = emp.getTotal();
	System.out.println(total);
	int totalPages = emp.getTotal_pages();
	System.out.println(totalPages);
	ArrayList<Datum> data = emp.getData();
	for (Datum datum : data) {
		System.out.println("---Flight---");
		System.out.println(datum.getId());
		System.out.println(datum.getFlightName());
		System.out.println(datum.getCountry());
		System.out.println(datum.getDestinations());
		System.out.println(datum.getuRL());
	}
	Support support = emp.getSupport();
	String url = support.getUrl();
	System.out.println(url);
	String text = support.getText();
	System.out.println(text);
	
}
	
}
