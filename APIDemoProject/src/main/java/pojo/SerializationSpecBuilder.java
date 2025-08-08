package pojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerializationSpecBuilder {

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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification ResSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		// TODO Auto-generated method stub
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RequestSpecification res = given().spec(req).body(ap);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(ResSpec).extract().response();
		
		String responseString = response.asString();
		
		System.out.println(responseString);
	}

}
