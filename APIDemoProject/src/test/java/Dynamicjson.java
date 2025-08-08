import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamicjson {
	
	
	@Test(dataProvider="dp")
	public void addbook(String isbn, String aisle) 
	{
		RestAssured.baseURI="http://216.10.245.166";
		String response = 
		given().header("Content-Type","application/json").body(Payload.addbook(isbn , aisle)).log().all()
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		
		JsonPath js  = ReUsableMethods.RawToJson(response);
		
		String id = js.get("ID");
		
		System.out.println(id);
		
		//DeleteBookAPI
		
	}
	
	@DataProvider(name = "dp")
	public Object getData() 
	{
		//Arrays - Collection of elements
		//MultiDimensional array - Collection of arrays.
		
		return new Object[][] {{"ojfwty","9363"},{"cwetee","4253"},{"okmfet","533"}};
	}

}
