package files;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

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
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").body(p).when().post("/maps/api/place/add/json")
		.then().log().all().statusCode(200).extract().response().asString();

		
		System.out.println(response);
	}

}
