package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DyanmicJson {
	
	@Test(dataProvider="BooksData")
	//AddBook
	public void addBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String addresponse = given().log().all().header("Content-Type","application/json").body(payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php").then().log().all()
		.assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(addresponse);
		String id=js.get("ID");
		System.out.println("ID is "+id);
	}
	@Test(dataProvider="BooksData")
	//DeleteBook
	public void deleteBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String deleteResponse =given().log().all().body(payload.DeleteBook(isbn, aisle)).when().post("/Library/DeleteBook.php ").then()
		.log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(deleteResponse);
		String output=js1.get("msg");
		System.out.println(output);
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"gfgff","2345"},{"asdsa","4567"},{"vekta","1148"}};
	}

}
