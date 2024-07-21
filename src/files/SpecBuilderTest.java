package files;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

	public static void main(String[] args) {
		
		AddPlace p= new AddPlace();
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setAccuracy(50);
		p.setName("Vishal house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		List<String> t=new ArrayList<String>();
		p.setTypes(t);
		p.setLocation(l);
		
		RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		 .setContentType(ContentType.JSON).build();
		
		ResponseSpecification  resSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		 RequestSpecification response = given().spec(req).body(p);
		 
		 Response Res = response.when().post("/maps/api/place/add/json")
		.then().log().all().spec(resSpec).extract().response();
		 
		 String finalres =Res.asString();

		
		System.out.println(finalres);
	}

}
