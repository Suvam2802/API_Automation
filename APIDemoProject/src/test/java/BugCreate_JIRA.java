import org.testng.annotations.Test;

import files.ReUsableMethods;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugCreate_JIRA {
	
	
	
	@Test
	public void createBug() {
		
		
		RestAssured.baseURI="https://lyvtestsuvam.atlassian.net";
		
		String res = given().header("Content-Type","application/json").header("Authorization",BugCreate_JIRA.Auth())
		.body(BugCreate_JIRA.Body())
		.when().post("rest/api/2/issue")
		.then().log().all().assertThat().statusCode(201).extract().asString();
		
		JsonPath js = ReUsableMethods.RawToJson(res);
		
		String issueID = js.getString("id");
		
		System.out.println(issueID);
		
		//Add Attachment
		
		
		given().header("X-Atlassian-Token","no-check").header("Authorization",BugCreate_JIRA.Auth())
		.pathParam("key", issueID)
		.multiPart("file",new File("C:\\Users\\HP\\Downloads\\images.png")).log().all()
		.post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200).body("[0].filename", equalTo("images.png"));
		
	}
	
	public static String Auth() 
	{
		return "Basic bGl2aW5nc3V2YW1AZ21haWwuY29tOkFUQVRUM3hGZkdGMHUzYmJDaWlmQnAtSTI3WTZ3ejd2UG5LMlVFN1kyRFlKVzFWdlpXNjE3U0VPeVZmUW8xUDFua3dscDFyMmtycGZOT3VGdW5pVWFQMkRNNG9VVGEwaWRUVEJ2dnQ0bGRFWVRrQk9fQ0N0MnlZQnNSUldFcXZBT1BZcy1FNGR2TXhmNHU4RnNYVEJvNG8tRmJ3NVUwaTFjZXVfdDhDdFE5SEJROW1UbEpmOFU5VT1FRkRGQzJFNA==";
	}
	
	public static String Body() 
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"MSP\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Form is not working - REST - Created Issue\",\r\n"
				+ "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API PostMan\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}\r\n"
				+ "";
	}
}
