package data;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ExcelDrivenTest {
	
	static String BookName = "Learn Appium Automation with Java";
	
	public static void main(String [] args) throws IOException 
	{
		DataDriven dv = new DataDriven();
		ArrayList data = dv.getdata("RestAddBook","RestAssured");
		
		
		HashMap<String, Object> JsonHasMap = new HashMap<>();
		/*
		 * JsonHasMap.put("name", "Learn Appium Automation with Java");
		 * JsonHasMap.put("isbn", "DDDSSSD"); JsonHasMap.put("aisle", "227");
		 * JsonHasMap.put("author", "John Doe");
		 */
		
		// To handle nested json, we can use hashmap : first we have to create a hashmap for the nested json
		//then we can add that hashmap to the main hashmap as value of a key
		//example: JsonHasMap.put("key", nestedJsonHashMap);
		
		
		//For Excel Driven Testing, we can use the data from the excel file - Thats why i have commented above hardcoded values
		
		JsonHasMap.put("name", data.get(1));
		JsonHasMap.put("isbn", data.get(2));
		JsonHasMap.put("aisle", data.get(3));
		JsonHasMap.put("author", data.get(4));
		
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json")
		.body(JsonHasMap)
		
		.when().post("Library/Addbook.php")
		
		.then().assertThat().statusCode(200).body("Msg", equalTo("successfully added"))
		.header("Server",  "Apache").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);   // For parsing JSON  // i have given string response to parse
		
		String placeID = js.getString("ID");
		
		System.out.println(placeID);
		
		
		//Get Book
		RestAssured.baseURI = "http://216.10.245.166";
		String response2 = given().log().all().header("Content-Type","application/json").queryParam("ID", placeID)
				.when().get("Library/GetBook.php")
				.then().log().all().assertThat().statusCode(200)
				.header("Server",  "Apache").extract().response().asString();
		
		//Delete Book
		RestAssured.baseURI = "http://216.10.245.166";
		String response3 = given().log().all().header("Content-Type","application/json").body("{\r\n"
				+ "\r\n"
				+ "    \"ID\" : \""+placeID+"\"\r\n"
				+ "\r\n"
				+ "}")
				.when().get("Library/DeleteBook.php")
				.then().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"))
				.header("Server",  "Apache").extract().response().asString();
				
		JsonPath js1 = new JsonPath(response3);   // For parsing JSON  // i have given string response to parse
		
		String msg = js1.getString("msg");
		
		System.out.println(msg);
	}

}
