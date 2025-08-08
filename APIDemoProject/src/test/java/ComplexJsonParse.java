import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(Payload.ComplexJsonPayload());
		
		
		//
		int csize = js.getInt("courses.size()");
		
		//Print the number of courses returned by API
		System.out.println("Number of courses: " + js.getInt("courses.size()"));
		
		
		//Print the purchase amount
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		
		//Print Title of first course >> Selenium Python 
		System.out.println(js.getString("courses[0].title"));
		
		
		System.out.println("*********************************************************************");
		//Print All course title and prices
		
		for(int i=0; i<csize; i++) 
		{
			String title = js.getString("courses["+i+"].title");
			
			int price = js.getInt("courses["+i+"].price");
			
			
			System.out.println(title +" == "+ price);
			
		}
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		for(int j=0; j<csize; j++) 
		{
			String title = js.get("courses["+j+"].title");
			
			if(title.equalsIgnoreCase("RPA")) 
			{
				System.out.println(js.getString("courses["+j+"].copies"));
				break;
			}
		}
		
		System.out.println("***********************************************************************");
		
		
		int totalPrice = 0;
		
		for(int k=0; k<csize; k++) 
		{
			
			int Price = js.getInt("courses["+k+"].price") * js.getInt("courses["+k+"].copies");
		
			
			totalPrice = totalPrice + Price;
		}
		
		
		System.out.println(totalPrice);
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(totalPrice, purchaseAmount);
		
		
		
		System.out.println("++++++++++++++++   VALIDATION IS COMPLETED   ++++++++++++++++");
	}

}
