import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.payload;

public class Basics {

	public static void main(String[] args) throws IOException {
			
        //Add place API	
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body( new String(Files.readAllBytes(Paths.get("C:\\Users\\vishal.saini_thought\\Documents\\applicaton.json"))))
		.when().post("maps/api/place/add/json").then().statusCode(200)
		.body("scope",equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
		 
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String placeId =js.get("place_id");
		
		System.out.println(placeId);
		
		
		//PUT place API
		
		String newAddress ="Palam,New-Delhi";
		 given().log().all().queryParam("Key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		
		
//		GET place API
		String getResponse =given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		
		JsonPath js1=new JsonPath(getResponse);
		String actualAddress=js1.get("address");
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
	}

}
