import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.GetCourses;
import pojo.webAutomation;

public class oAuth_API2_SD {
	
	
	
	@Test
	public void testOAuthAPI() {
			
		String res = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(res);
		
		String token = js.getString("access_token");
		
		System.out.println(token);
		
		
		GetCourses gc = given().queryParam("access_token", token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).extract().as(GetCourses.class);
		
		System.out.println(gc.getLinkedIn());
		
		System.out.println(gc.getCourses().getWebAutomation().get(0).getCourseTitle());
		
		List<webAutomation> gccl = gc.getCourses().getWebAutomation();
		
		for(int i=0; i<gccl.size(); i++) 
		{
			if(gc.getCourses().getWebAutomation().get(i).getCourseTitle().equalsIgnoreCase("Cypress")) 
			{
				System.out.println(gc.getCourses().getWebAutomation().get(i).getPrice());
			}
		}
		
		
		
		
		
		
		
		
		
		
		String [] courseTitle = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		ArrayList<String> arr = new ArrayList<String>();
		
		List<String> brr = Arrays.asList(courseTitle);
		
		System.out.println("**********************************************************************************");
		
		for(int j=0; j<gccl.size(); j++) 
		{
			
			arr.add(gccl.get(j).getCourseTitle());
				
		}
		
		Assert.assertTrue(arr.equals(brr));
		
		System.out.println("TEST PASSED: Course titles match the expected values.");
		
		System.out.println("**********************************************************************************");
	}
	
	

}
