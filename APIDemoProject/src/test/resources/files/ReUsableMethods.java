package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath RawToJson(String Response) 
	{
		
		JsonPath js = new JsonPath(Response);
		return js;
		
	}

}
