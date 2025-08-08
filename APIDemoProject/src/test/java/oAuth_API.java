import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class oAuth_API {
	
	
	
	@Test
	public void testOAuthAPI() {
			
		String res = given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials")
		.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(res);
		
		String token = js.getString("access_token");
		
		System.out.println(token);
		
		
		String CourseRes = given().queryParam("access_token", token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).extract().asString();
		
		System.out.println(CourseRes);
	}
	
	

}
