package oAuth2.o;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.*;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class Oauth2Test {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver=new ChromeDriver();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--incognito");
//
//		driver = new ChromeDriver(options);
		
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope="
				+ "https://www.googleapis.com/auth/userinfo.email&auth_url="
				+ "https://accounts.google.com/o/oauth2/v2/auth&client_id="
				+ "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj."
				+ "apps.googleusercontent.com&response_type=code&redirect_uri="
				+ "https://rahulshettyacademy.com/getCourse.php");
		
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("vishalsainiproffesional@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Vishalsaini@081296");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[3]/div/div/div[2]/div/div/button/span")).click();
		//Thread.sleep(3000);
		String url=driver.getCurrentUrl();
		
		String code1=url.split("code=")[1];
		String code=code1.split("&scope")[0];
		
		System.out.println(code);
		
		
		
		 String accesTokenResponse = given().urlEncodingEnabled(false).log().all().queryParams("code", code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").then()
		.log().all().extract().response().asPrettyString();
		
		JsonPath js=new JsonPath(accesTokenResponse);
		String acessToken=js.get("access_token");
		
		
		String response =given().log().all().queryParam("access_token", acessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php")
		.then().log().all().extract().response().asPrettyString();
		
		System.out.println(response);
	}

}
