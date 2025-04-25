package com.omrbranch;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SingleFlightAPI {
	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fileReader =new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Employee.json");
		JSONParser jsonParser=new JSONParser();
		Object object = jsonParser.parse(fileReader);
		JSONObject jsonObject = (JSONObject)object;
		Object object2 = jsonObject.get("data");
		
//		System.out.println(object2);
		
		
		
		JSONObject jsonObject2=(JSONObject)object2;
		Object object3 = jsonObject2.get("flightName");
		
		System.out.println(object3);
		JSONObject jsonObject3=(JSONObject)object2;
		Object object4 = jsonObject3.get("Country");
		
		System.out.println(object4);
		JSONObject jsonObject4=(JSONObject)object2;
		Object object5 = jsonObject4.get("Destinations");
		
		System.out.println(object5);
		JSONObject jsonObject5=(JSONObject)object2;
		Object object6 = jsonObject5.get("URL");
		
		System.out.println(object6);
		JSONObject jsonObject6=(JSONObject)object2;
		Object object7 = jsonObject6.get("Created_Date");
		
		System.out.println(object7);
		
		JSONObject jsonObject7=(JSONObject)object2;
		Object object8 = jsonObject7.get("Updated_Date");
		
		System.out.println(object8);
		
		JSONObject jsonObject8=(JSONObject)object2;
		Object object9 = jsonObject8.get("id");
		
		System.out.println(object9);
		
		
//		JSONObject jsonObject9=(JSONObject)object2;
		Object object10 = jsonObject.get("support");
//		System.out.println(object10);
		JSONObject jsonObject10=(JSONObject)object10;
		Object object11 = jsonObject10.get("url");
		
		System.out.println(object11);
		JSONObject jsonObject11=(JSONObject)object10;
		Object object12 = jsonObject11.get("text");
		
		System.out.println(object12);
		
		
		
		
	}

}
