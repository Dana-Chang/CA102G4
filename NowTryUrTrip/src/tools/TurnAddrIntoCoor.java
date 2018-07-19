package tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TurnAddrIntoCoor {
	
	private String lat;
	private String lng;
	URL url;
	InputStream is = null;
	BufferedReader br;
	String line;

	public String latChanger(String addr) throws IOException, ParseException{
		String searchString = "http://maps.googleapis.com/maps/api/geocode/json?address="
				+ java.net.URLEncoder.encode(addr, "UTF-8") + "&sensor=false&language=zh-TW";
		System.out.println(addr);
		System.out.println(searchString);
		url = new URL(searchString);
		is = url.openStream(); 
		StringBuilder jsonBuilder = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		while ((line = br.readLine()) != null) {
			jsonBuilder.append(line);
		}
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(jsonBuilder.toString());
		
		JSONArray jsonRs = (JSONArray) jsonObj.get("results");
		JSONObject jsonObject = (JSONObject) jsonRs.get(0);
		JSONObject geometry = (JSONObject) jsonObject.get("geometry");
		JSONObject location = (JSONObject) geometry.get("location");
		lat = location.get("lat").toString();
		
		return lat;
	}
	
	
	public String lngChanger(String addr) throws ParseException, IOException{
		String searchString = "http://maps.googleapis.com/maps/api/geocode/json?address="
				+ java.net.URLEncoder.encode(addr, "UTF-8") + "&sensor=false&language=zh-TW";
		url = new URL(searchString);
		is = url.openStream(); // throws an IOException
		StringBuilder jsonBuilder = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		JSONParser parser = new JSONParser();
		while ((line = br.readLine()) != null) {
			jsonBuilder.append(line);
		}
		JSONObject jsonObj = (JSONObject) parser.parse(jsonBuilder.toString());
		
		JSONArray jsonRs = (JSONArray) jsonObj.get("results");
		JSONObject jsonObject = (JSONObject) jsonRs.get(0);
		JSONObject geometry = (JSONObject) jsonObject.get("geometry");
		JSONObject location = (JSONObject) geometry.get("location");
		lng = location.get("lng").toString();
		
		return lng;
	}
	
	public String formatted_address(String addr) throws ParseException, IOException{
		String searchString = "http://maps.googleapis.com/maps/api/geocode/json?address="
				+ java.net.URLEncoder.encode(addr, "UTF-8") + "&sensor=false&language=zh-TW";
		url = new URL(searchString);
		is = url.openStream(); // throws an IOException
		StringBuilder jsonBuilder = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		while ((line = br.readLine()) != null) {
			jsonBuilder.append(line);
			System.out.println(line);
		}
		System.out.println(jsonBuilder.toString());
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(jsonBuilder.toString());
		
		
		JSONArray jsonRs = (JSONArray) jsonObj.get("results");
		JSONObject jsonObject = (JSONObject) jsonRs.get(0);
		String formatted_address = jsonObject.get("formatted_address").toString();
		
		System.out.println(formatted_address);
		
		return formatted_address;
	}
	
}
