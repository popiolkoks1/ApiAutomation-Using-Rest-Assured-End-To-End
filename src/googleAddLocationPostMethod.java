import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
public class googleAddLocationPostMethod {

	
	@Test
	public void apiPost()
	{
		//Base URL or HOST
		RestAssured.baseURI = "https://maps.googleapis.com/";
		// here inside given we have only one param to pass key
        given().
		      queryParam("key","AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA").
		      
		/*As we are working here with the POST method that's why we have to pass every thing inside the body
		 In java you can't place double quotas inside double quotas for solving that issue you have to provide forward slash before 
		 every double quotas even after solving that issue you will still get one more error because here we are passing 
		 multiple line of string inside a single method. So,interns to solving that problem you have to put every line string inside 
		 a double quotas followed by a (+) plus symbol then only java treats as it a single line string*/      
		
		body("{"+
				  "\"location\": {"+
				    "\"lat\": -33.8669710,"+
				    "\"lng\": 151.1958750"+
				  "},"+
				  "\"accuracy\": 50,"+
				  "\"name\": \"Google Shoes!\","+
				  "\"phone_number\": \"(02) 9374 4000\","+
				  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				  "\"types\": [\"shoe_store\"],"+
				  "\"website\": \"http://www.google.com.au/\","+
				  "\"language\": \"en-AU\""+
			"}").
		when().
		      post("/maps/api/place/add/json").
		then().
		      assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		      body("status",equalTo("OK"));
		
	}
}
