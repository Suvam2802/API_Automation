package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.addPlace;

public class TestDataBuild {
	
	
	
	public addPlace AddPlacePayload(String name, String language, String address) 
	{
		addPlace ap = new addPlace();
		
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("1234567890");
		ap.setWebsite("www.myweb.com");
		
		List<String> mylist = new ArrayList<String>();
		mylist.add(0, "Type 1");
		mylist.add(1, "Type 2");
		
		ap.setTypes(mylist);
		
		Location loc = new Location();
		loc.setLat(-35.256486228);
		loc.setLng(+65.585364446);
		
		ap.setLocation(loc);
		
		return ap;
	}
	
	public String DeletePlacePayload(String placeID) 
	{
		String payload = "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}\r\n"
				+ "";
		
		return payload;
	}

}
