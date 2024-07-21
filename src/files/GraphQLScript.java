package files;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class GraphQLScript {

	public static void main(String[] args) {
		
//Query		
		RequestSpecification res1 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/gq/graphql")
		.setContentType(ContentType.JSON).setBody(GraphQLBody.Query()).build();
		
		ResponseSpecification req = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		String response1=given().log().all().spec(res1).when().post().then().log().all()
				.assertThat().spec(req).extract().response().asPrettyString();
		
		JsonPath js1= new JsonPath(response1);
		String name=js1.get("data.character.name");
		System.out.println(name);
		String dim=js1.get("data.location.dimension");
		System.out.println(dim);
		String ep= js1.get("data.episode.name");
		System.out.println(ep);
		
		Assert.assertEquals(name, "Vortexxx");

//Mutation
		RequestSpecification res2 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/gq/graphql")
				.setContentType(ContentType.JSON).setBody(GraphQLBody.Mutation()).build();
		
		
		String response2=given().log().all().spec(res2).when().post().then().log().all()
				.assertThat().spec(req).extract().response().asPrettyString();
		
	}

}
