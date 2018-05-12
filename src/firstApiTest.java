import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class firstApiTest {
    // Here we are doing the same thing but just add TestNG as in the previous example we are not able to see our result output 
	@Test
	
	public void apiTest()
	{
		RestAssured.baseURI = "https://maps.googleapis.com/";
		
		given().
	      param("location","-33.8670522,151.1957362").
	      param("radius","500").
	      param("key","AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA").
	     
	      when().
		     get("/maps/api/place/nearbysearch/json").
		 
		  then().
		     assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		     body("results[0].name",equalTo("Sydney")).and().
		     body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		     header("Server", "scaffolding on HTTPServer2");
		
		// Note - While comparing the vale no where we need to use equalTo() method except under body();
		     
	}
}
