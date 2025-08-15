Feature: Validating Place API's

@AddPlace @Regression
Scenario: Verify if Place is being Succesfully added Using AddPlaceAPI

		Given AddPlace Payload with "<name>", "<language>" and "<address>"
		When User calls "AddPlaceAPI" with "POST" http request
		Then The API call get success with status code 200		
		And "status" in response body is "OK"		
		And  "scope" in response body is "APP"		
		And Verify the plac_ID created maps to "<name>" using "getPlaceAPI"
		
Examples:
    | name       | language        | address      | 
    | AaHouse    | Odia-Odisha     | 1234, Odisha |
#   | Chome      | ENG-ODIA        | 108, My AYT  |


@DeletePlace @Regression
Scenario: Verify if Delete Place Functionality is working	

			Given DeletePlace Payload
			When User calls "deletePlaceAPI" with "POST" http request
			Then The API call get success with status code 200
			And "status" in response body is "OK"
			
			
			
			