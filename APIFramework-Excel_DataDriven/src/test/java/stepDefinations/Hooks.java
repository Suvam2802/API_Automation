package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException 
	{
		//Execute this code only when Place_ID is null
		//Write a code that will give Place_ID
		
		StepDefination sd = new StepDefination();
		if(StepDefination.place_id==null) {
		
		sd.add_place_payload_with_and("SWAIN", "ESPANIOL", "KANDARPUR");
		
		sd.user_calls_with_request("AddPlaceAPI", "POST");
		
		sd.verify_the_plac_id_created_maps_to_using("SWAIN", "getPlaceAPI");
	}
		
	}

}
