import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;


public class EcommerceAPITest {

	public static void main(String[] args) {
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("vishal081296@gmail.com");
		loginRequest.setUserPassword("Qwerty@12345");
		
		
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
		
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all()
				.extract().response().as(LoginResponse.class);
		
		System.out.println(loginResponse.getToken());
		String token= loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId=loginResponse.getUserId();
		
		
		
		//Add Product
		
		RequestSpecification addProdcutBaseReq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization",token).build();
		
		RequestSpecification reqAddProduct = given().log().all().spec(addProdcutBaseReq).param("productName", "qwerty")
				.param("productAddedBy", userId).param("productCategory","Electronics" )
		.param("productSubCategory","Laptop").param("productPrice","115000").param("productDescription","LenovoThinkPad")
		.param("productFor", "Gamers")
		.multiPart("productImage",new File("C:\\Users\\vishal.saini_thought\\Desktop\\4.png"));
		
		
		String addProductResponse=reqAddProduct.when().post("/api/ecom/product/add-product")
				.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js= new JsonPath(addProductResponse);
		String productId=js.get("productId");
		
		System.out.println(productId);
		
		//Create Order
		
		
		
		RequestSpecification createOrderBaseReq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("Authorization",token).build();
		
		orderDetails orderdetails = new orderDetails();
		orderdetails.setCountry("India");
		orderdetails.setProductOrderedId(productId);
		
		List<orderDetails> orderDetailList = new ArrayList<orderDetails> ();
		orderDetailList.add(orderdetails);
		
		
		Orders order = new Orders();
		order.setOrders(orderDetailList);
	
		
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(order);
		
		String responseAddOrder =createOrderReq.when().post("/api/ecom/order/create-order").then().log().all()
				.extract().response().asString();
		
		System.out.println(responseAddOrder);
		
		//Delete Order
		
		
		RequestSpecification deleteOrderBaseReq=  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("Authorization",token).build();
		
		RequestSpecification deleteOrderReq = given().log().all().spec(deleteOrderBaseReq).pathParam("productId", productId);
		
		
		String deleteProductResponse= deleteOrderReq.when().delete("/api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1= new JsonPath(deleteProductResponse);
		String message=js1.get("message");
		
		System.out.println(message);
		
		Assert.assertEquals("Product Deleted Successfully", message);
		
		
		
	}

}
