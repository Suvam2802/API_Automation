package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.addPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification ResSpec ;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("AddPlace Payload with {string}, {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.AddPlacePayload(name, language, address));
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_request(String resources, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resources);
		System.out.println(resourceAPI.getResource());
		
		ResSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST")) 
			{
		response = res.when().post(resourceAPI.getResource());
			}
		else if(method.equalsIgnoreCase("GET")) 
		{
			response = res.when().get(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("DELETE")) 
		{
			response = res.when().post(resourceAPI.getResource());
		}
			//	.then().spec(ResSpec).extract().response();
	}
	@Then("The API call get success with status code {int}")
	public void the_api_call_get_success_with_status_code(Integer int1) {
	
	   assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	  @Then("{string} in response body is {string}") 
	  public void in_response_body_is(String KeyValue, String ExpectedValue) 
	  { 
		  assertEquals(getJsonPath(response,KeyValue),ExpectedValue); 
	  }
	  
	  
	  @Then("Verify the plac_ID created maps to {string} using {string}")
	  public void verify_the_plac_id_created_maps_to_using(String expectedName, String resource) throws IOException 
	  {
		  place_id = getJsonPath(response,"place_id");
		  res = given().spec(requestSpecification()).queryParam("place_id", ""+place_id+"");
				  user_calls_with_request(resource,"GET");
				  String ActualName= getJsonPath(response,"name");
				  
				  assertEquals(ActualName, expectedName);
	  }

	  @Given("DeletePlace Payload")
	  public void delete_place_payload() throws IOException {

		  res = given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
		  
	  }
	 

}
