package pojo;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcomApiTest {

	public static void main(String[] args) {
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		
		LoginRequeest lr = new LoginRequeest();
		lr.setUserEmail("biswa.relevel.classes@gmail.com");
		lr.setUserPassword("Biswa@2022");
		
		RequestSpecification resq = given().log().all().spec(req).body(lr);
		
		LoginResponse lresp = new LoginResponse();
		
		lresp = resq.when().post("api/ecom/auth/login").then().log().all().assertThat().statusCode(200).extract().as(LoginResponse.class);
		
		System.out.println(lresp.getToken());
		
		String token = lresp.getToken();
		String userid = lresp.getUserId();
		
		System.out.println(lresp.getUserId());
		
		
		// ADD Product API
		RequestSpecification AddProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("Authorization", token).build();
		
		RequestSpecification APresq = given().log().all().spec(AddProductReq).formParam("productName", "Computer").formParam("productAddedBy", userid).formParam("productCategory", "fashion")
		.formParam("productSubCategory", "shirts").formParam("productPrice", "11500").formParam("productDescription", "Addias Originals")
		.formParam("productFor", "women").multiPart("productImage", new File("C:\\Users\\HP\\Downloads\\images.png"));
		
		AddProductResponse addProductResponse = APresq.when().post("api/ecom/product/add-product").then().log().all().assertThat().statusCode(201).extract().as(AddProductResponse.class);
		
		String CproductID = addProductResponse.getProductId();
		
		// CREATE ORDER
		
		OrdersDetails odtl = new OrdersDetails();
		odtl.setCountry("India");
		odtl.setProductOrderedId(CproductID);
		
		List<OrdersDetails> orderdetails = new ArrayList<OrdersDetails>();
		orderdetails.add(odtl);
		
		Orders od = new Orders();	
		od.setOrders(orderdetails);
		
		RequestSpecification CrtOrder = given().log().all().spec(req).header("Authorization", token).body(od);
		String orderCreation = CrtOrder.when().post("api/ecom/order/create-order").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(orderCreation);
		
		System.out.println(js.getString("message"));
		
		//Delete Product
		RequestSpecification DeleteP = given().log().all().spec(AddProductReq).pathParam("productID",CproductID);
		DeleteP.when().delete("api/ecom/product/delete-product/{productID}").then().log().all().assertThat().statusCode(200);
	}

}
