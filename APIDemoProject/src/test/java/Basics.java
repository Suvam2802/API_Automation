
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class Basics {
	
public static void main(String[] args) {
		
	    // Add place- API is added with Address -> get Place API - it will check the place is added or not in response
	
		//Validate Add Place API
		//*******************************************
		
		//Given  >> it will take all input details
		//When   >> it will submit the API request  >> resources and http method
		//Then   >> it will validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		
		.when().post("maps/api/place/add/json")
		
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server",  "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);   // For parsing JSON  // i have given string response to parse
		
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		
		//Update API  ***********************************************************************************************
		String NewAddress = "Updated-108 - Ayatpur, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+NewAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		
		.when().put("maps/api/place/update/json")
		
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"))
		.header("Server",  "Apache/2.4.52 (Ubuntu)");
		
		
		//Get API  **************************************************************************************************
		
		String getresponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		
		.when().get("maps/api/place/get/json")
		
		.then().assertThat().log().all().statusCode(200).body("address", equalTo(NewAddress))
		.header("Server",  "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath jsp = ReUsableMethods.RawToJson(getresponse);
		
		String UpdatedAddress = jsp.getString("address");
		
		System.out.println(UpdatedAddress);
		
		Assert.assertEquals(UpdatedAddress, NewAddress);
		
		
		System.out.println("************* Validation of API is successful *************");
		
		
		
}

}
