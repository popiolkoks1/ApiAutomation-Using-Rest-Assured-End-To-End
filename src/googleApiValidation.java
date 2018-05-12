import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;

public class googleApiValidation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// We need to 1st define the base URI which is nothing but some url.
		
		RestAssured.baseURI = "https://maps.googleapis.com/";
		
		/*Under given() generally we can provide Request header,Parameter,Request Cookies and Body(In case of post method)
		 here for our api we only have to send parameters as google api need only param to search for any location but in general 
		 most of the time for other api's we have to pass header as well and if we are delaying with post method than we have to
		 provide body too. For given() block we have to import package:- "import static io.restassured.RestAssured.given" */
		
		given().
		      param("location","-33.8670522,151.1957362").
		      param("radius","500").
		      param("key","AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA").
		      
		/*Under when() block we need to provide resource, i mean in side that we have to specify request type i.e get(),post() etc */      
		
		when().
		     get("/maps/api/place/nearbysearch/json").
		     
		/*In side then() block we will get the api response and at the end we have to put some assertion to validate api response 
		 here we are using multiple validation methods 1st i am validation with the status code then with content type like JSON or XML
		 and with the response body. Note :- Make sure that every validation method must be saperated with and()*/  
		     
		then().
		     assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		     
		/*This is how we traverse inside the JSON if you open the api response in proper JSON format then you will see entire 
		  all the responses are under 4 object "html_attributions","next_page_token","results","status" and result is an array 
		  of size 20 and we are trying to validate our lat result under the zero's index and even if you expand that you will 
		  get lat is under geometry and inside geometry under location. Although if we are going to run this code it through error
		  why, because every time the lon and lat value get deferred so in this type of situation better to validate with 
		  some different object i am going to validate it using the name      
		  body("results[0].geometry.location.lat",equalTo("-33.8688197")); here when you use equalTo method then it through
		  error so you have to import package:- "import static org.hamcrest.Matchers.equalTo;"*/
		  
		body("results[0].name",equalTo("Sydney"));
		
		

	}

}
