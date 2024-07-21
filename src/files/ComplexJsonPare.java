package files;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonPare {

	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(payload.Complex());
		
		//No. of Courses
		
		int Count = js.getInt("courses.size()");
		System.out.println(Count);
		
		//Print Purchase Amount
		int amount =js.getInt("dashboard.purchaseAmount");                   
		System.out.println(amount);
		
		//Print title of the first course
		String CourseName =js.get("courses.title[0]");
		System.out.println(CourseName);
		
		//Print all Course title & their respective prices
		
		for(int i=0;i<Count;i++)
		{
			String name =js.get("courses.title["+i+"]");
			int price = js.getInt("courses.price["+i+"]");
			System.out.println(name);
			System.out.println(price);
		}
		
		//Print no of copies sold by RPA course

		for(int i=0;i<Count;i++)
		{
			String name1 =js.get("courses.title["+i+"]");
			if(name1.equalsIgnoreCase("RPA"))
			{
				int Price =js.get("courses.copies["+i+"]");
				System.out.println(Price);
				break;
			}
			
		}
		
		
		//verify if sum of all courses price matches with Purchase amount
		int TotalAmount = 0;
		for(int i=0;i<Count;i++)
		{
			int Price =js.getInt("courses.price["+i+"]");
			int copies =js.getInt("courses.copies["+i+"]");
			TotalAmount = TotalAmount +(Price*copies);	
		}
		
		System.out.println(TotalAmount);
		Assert.assertEquals(TotalAmount, amount);
		
		

		
		

	}

}
