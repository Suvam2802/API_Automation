package pojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;

public class Serialization {

	public static void main(String[] args) {
		
		addPlace ap = new addPlace();
		
		ap.setAccuracy(50);
		ap.setAddress("Ayatpur- 754100");
		ap.setLanguage("Odia");
		ap.setName("Suvam swain");
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
		
		
		
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(ap)
		
		.when().post("maps/api/place/add/json")
		
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server",  "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
	}

}
