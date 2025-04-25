package com.omrbranchwrite;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlightWithWriteValues {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		File file= new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Hello1.json");
		ObjectMapper objectMapper = new ObjectMapper();
		Employe emp = new Employe();
		objectMapper.writeValue(file, emp);
//		ArrayList<Datum1> d = new ArrayList<Datum1>();
//		Datum1 d1 = new Datum1(49, "greens", "omr", "86", "https://en.wikipedia.org/wiki/Air_India");
//		Datum1 d2 = new Datum1(481, "Lufthansa", "India", "87", "https://en.wikipedia.org/wiki/Air_India");
//		Datum1 d3 = new Datum1(516, "AIR CANADA", "India", "19", "https://en.wikipedia.org/wiki/Air_India");
//		Datum1 d4 = new Datum1(548, "AirIndia", "India", "87", "https://en.wikipedia.org/wiki/Air_India");
//		Datum1 d5 = new Datum1(557, "AirIndia", "India", "87", "https://en.wikipedia.org/wiki/Air_India");
//		Datum1 d6 = new Datum1(561, "AirIndia", "India", "87", "https://en.wikipedia.org/wiki/Air_India");
//
//		d.add(d1);
//		d.add(d2);
//		d.add(d3);
//		d.add(d4);
//		d.add(d5);
//		d.add(d6);
//
//		Support1 support1 = new Support1("https://www.omrbranch.com",
//				"For Joining Automation Course, Please Contact-Velmurugan 9944152058");
//		System.out.println("done");
	}
}
