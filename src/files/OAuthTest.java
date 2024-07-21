package files;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class OAuthTest {
	
	
	@Test
	public void Oauth()
	{
		//RestAssured.baseURI="https://rahulshettyacademy.com";
	String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		                     .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		                     .formParams("grant_type", "client_credentials")
		                     .formParams("scope", "trust")
		                     .when().log().all()
		                     .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		
		JsonPath js= new JsonPath(response);
		String token=js.get("access_token");
		
		
	GetCourse gc=	given().log().all()
		.queryParams("access_token",token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.as(GetCourse.class);
	
	ArrayList<String> actual= new ArrayList<String>();
	actual.add("Selenium Webdriver Java");
	actual.add("Cypress");
	actual.add("Protractor");
	ArrayList<String> expected= new ArrayList<String>();
	
	
	//System.out.println(gc.getInstructor());
	//System.out.println(gc.getLinkedIn());
	//System.out.println(gc.getCourses().getApi().get(1).getPrice());
	// List<api> apiCourses = gc.getCourses().getApi();
//	 
//	 for(int i=0;i<apiCourses.size();i++)
//	 {
//		 if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI WebServices testing"))
//		 {
//			  System.out.println(apiCourses.get(i).getPrice());
//		 }
//	 }
	 
	 
	 List<webAutomation> webAuto = gc.getCourses().getWebAutomation();	
	 
	 for(int i=0;i<webAuto.size();i++)
	 {
		 expected.add(webAuto.get(i).getCourseTitle());
	 }
	 
	 Assert.assertFalse(actual.equals(expected));
	 
	}
	



}
