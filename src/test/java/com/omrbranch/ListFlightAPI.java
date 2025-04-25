package com.omrbranch;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ListFlightAPI {
public static void main(String[] args) throws IOException, ParseException {
	FileReader fileReader=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\ListFlight.json");
	JSONParser jsonParser = new JSONParser();
	Object object = jsonParser.parse(fileReader);
	JSONObject jsonObject=(JSONObject) object;
	Object object2 = jsonObject.get("page");
	System.out.println(object2);
	Object object3 = jsonObject.get("per_page");
	System.out.println(object3);
	Object object4 = jsonObject.get("total");
	System.out.println(object4);
	Object object5 = jsonObject.get("total_pages");
	System.out.println(object5);
	Object object6 = jsonObject.get("data");
//	System.out.println(object6);
	JSONArray jsonArray = (JSONArray) object6;
	/*Object object7 = jsonArray.get(0);
//	System.out.println(object7);
	JSONObject jsonObject2=(JSONObject) object7;
	Object object8 = jsonObject2.get("id");
	System.out.println(object8);
	JSONObject jsonObject3=(JSONObject) object7;
	Object object9 = jsonObject3.get("flightName");
	System.out.println(object9);
	JSONObject jsonObject4=(JSONObject) object7;
	Object object10 = jsonObject4.get("Country");
	System.out.println(object10);
	JSONObject jsonObject5=(JSONObject) object7;
	Object object11 = jsonObject5.get("Destinations");
	System.out.println(object11);
	JSONObject jsonObject6=(JSONObject) object7;
	Object object12 = jsonObject6.get("URL");
	System.out.println(object12);
	Object object13 = jsonArray.get(1);
//	System.out.println(object13);
	JSONObject jsonObject14=(JSONObject) object13;
	Object object14 = jsonObject14.get("id");
	System.out.println(object14);
	JSONObject jsonObject15=(JSONObject) object13;
	Object object15 = jsonObject15.get("flightName");
	System.out.println(object15);
	JSONObject jsonObject16=(JSONObject) object13;
	Object object16 = jsonObject16.get("Country");
	System.out.println(object16);
	JSONObject jsonObject17=(JSONObject) object13;
	Object object17 = jsonObject17.get("Destinations");
	System.out.println(object17);
	JSONObject jsonObject18=(JSONObject) object13;
	Object object18 = jsonObject18.get("URL");
	System.out.println(object18);
	Object object19 = jsonArray.get(2);
//	System.out.println(object19);
	JSONObject jsonObject20=(JSONObject) object19;
	Object object20 = jsonObject20.get("id");
	System.out.println(object20);
	JSONObject jsonObject21=(JSONObject) object19;
	Object object21 = jsonObject21.get("flightName");
	System.out.println(object21);
	JSONObject jsonObject22=(JSONObject) object13;
	Object object22 = jsonObject22.get("Country");
	System.out.println(object22);
	JSONObject jsonObject23=(JSONObject) object13;
	Object object23 = jsonObject23.get("Destinations");
	System.out.println(object23);
	JSONObject jsonObject24=(JSONObject) object13;
	Object object24 = jsonObject24.get("URL");
	System.out.println(object24);
	Object object25 = jsonArray.get(3);
//	System.out.println(object25);
	JSONObject jsonObject26=(JSONObject) object25;
	Object object26 = jsonObject26.get("id");
	System.out.println(object26);
	JSONObject jsonObject27=(JSONObject) object25;
	Object object27 = jsonObject27.get("flightName");
	System.out.println(object27);
	JSONObject jsonObject28=(JSONObject) object25;
	Object object28 = jsonObject28.get("Country");
	System.out.println(object28);
	JSONObject jsonObject29=(JSONObject) object25;
	Object object29 = jsonObject29.get("Destinations");
	System.out.println(object29);
	JSONObject jsonObject30=(JSONObject) object25;
	Object object30 = jsonObject30.get("URL");
	System.out.println(object30);
	Object object31 = jsonArray.get(4);
//	System.out.println(object31);
	JSONObject jsonObject32=(JSONObject) object31;
	Object object32 = jsonObject32.get("id");
	System.out.println(object32);
	JSONObject jsonObject33=(JSONObject) object31;
	Object object33 = jsonObject33.get("flightName");
	System.out.println(object33);
	JSONObject jsonObject34=(JSONObject) object31;
	Object object34 = jsonObject34.get("Country");
	System.out.println(object34);
	JSONObject jsonObject35=(JSONObject) object31;
	Object object35 = jsonObject35.get("Destinations");
	System.out.println(object35);
	JSONObject jsonObject36=(JSONObject) object31;
	Object object36 = jsonObject36.get("URL");
	System.out.println(object36);
	Object object37 = jsonArray.get(5);
//	System.out.println(object37);
	JSONObject jsonObject38=(JSONObject) object37;
	Object object38 = jsonObject38.get("id");
	System.out.println(object38);
	JSONObject jsonObject39=(JSONObject) object37;
	Object object39 = jsonObject39.get("flightName");
	System.out.println(object39);
	JSONObject jsonObject40=(JSONObject) object37;
	Object object40 = jsonObject40.get("Country");
	System.out.println(object40);
	JSONObject jsonObject41=(JSONObject) object37;
	Object object41 = jsonObject41.get("Destinations");
	System.out.println(object41);
	JSONObject jsonObject42=(JSONObject) object37;
	Object object42 = jsonObject42.get("URL");
	System.out.println(object42);*/
	Object object43 = jsonObject.get("support");
//	System.out.println(object43);
	JSONObject jsonObject7= (JSONObject) object43;
	Object object44 = jsonObject7.get("url");
	System.out.println(object44);
	JSONObject jsonObject8= (JSONObject) object43;
	Object object45 = jsonObject8.get("text");
	System.out.println(object45);
	
	for (int i = 0; i < jsonArray.size(); i++) {
		System.out.println("\n--- Flight " + (i + 1) + " ---");
        JSONObject flight = (JSONObject) jsonArray.get(i);
        System.out.println("ID: " + flight.get("id"));
        System.out.println("Flight Name: " + flight.get("flightName"));
        System.out.println("Country: " + flight.get("Country"));
        System.out.println("Destinations: " + flight.get("Destinations"));
        System.out.println("URL: " + flight.get("URL"));
		
	}

}


}
