package files;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Thoughtsphere {

	public static void main(String[] args) {
		
		
				RestAssured.baseURI = "https://test-informal.thoughtsphere.com";
				 given().log().all().header("user", "ts@ts.com").header("password","Welcome@01")
						.header("Content-Type","application/json").body("{\r\n"
								+ "    \"studyId\":[\"SCLI\"]\r\n"
								+ "}")
				.when().post("/clinhub_api/datacatalog/Adverse_eVents.json").then().log().all().assertThat().statusCode(200);
				//.extract().response().asString();	
				
//				JsonPath js= new JsonPath(response);
//				List<?> term =js.get("dataRecords");
//				//js.getj
//				System.out.println(term.get(0));
				
	}

}
