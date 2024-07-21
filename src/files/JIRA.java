package files;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JIRA {

	public static void main(String[] args) throws IOException {
		//To Log Bug in JIRA using Automated Scripts
		RestAssured.baseURI="https://vishalsaini-learning.atlassian.net";
		String Response=given().log().all().header("Content-Type","application/json").header("Authorization",AuthorizationToken.Token())
		.body(AuthorizationToken.Body()).when().post("/rest/api/2/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();	
		
		JsonPath js= new JsonPath(Response);
		String id =js.get("id");
		System.out.println("ID is"+id);
		
		//To Send Attachments to JIRA in a log defect
		given().log().all().header("X-Atlassian-Token","no-check").header("Authorization",AuthorizationToken.Token()).pathParam("key", id)
		.multiPart("file",new File("C:\\Users\\vishal.saini_thought\\Downloads\\SvgFile5454060788590126961.pdf"))
		.when().post("/rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		
		//GET Data
		given().log().all().header("Content-Type","application/json").pathParam("key", id).header("Authorization",AuthorizationToken.Token())
		.when().get("/rest/api/3/issue/{key}").then().log().all().assertThat().statusCode(200);
	}

}
