import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class extractApiResponse {

	/*
	 * Here the Test case is, you have to add an address using google place add
	 * api and then after you have to delete that added place. For archive that
	 * i am storing the 1st api response in side a variable and pass that value
	 * as an input to 2nd api using extract() block
	 */
	@Test
	public void grabOneApiResponsePassToAnotherApi() {
		String bodyValue = "{" + "\"location\": {" + "\"lat\": -33.8669710," + "\"lng\": 151.1958750" + "},"
				+ "\"accuracy\": 50," + "\"name\": \"Google Shoes!\"," + "\"phone_number\": \"(02) 9374 4000\","
				+ "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," + "\"types\": [\"shoe_store\"],"
				+ "\"website\": \"http://www.google.com.au/\"," + "\"language\": \"en-AU\"" + "}";
		// Task-1: Grab the response

		RestAssured.baseURI = "https://maps.googleapis.com/";

		// here the entire api response is store in the variable of Response
		// type.
		Response firstApiResult = given().queryParam("key", "AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA").body(bodyValue)
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();

		// Printing the response but this will not in readable format it's raw
		// data.
		// System.out.println(firstApiResult);

		// Converting raw data to string. asString() method response into string
		String responseString = firstApiResult.asString();
		System.out.println(responseString);

		// Task-2: Get the place_id from the response

		/*
		 * We want place_id but currently we have our response in string format.
		 * So we have to convert string to json 1st JsonPath js = new
		 * JsonPath(responseString); method is using for convert json to
		 * string and then after we have to fetch place_id from there
		 */

		JsonPath js = new JsonPath(responseString);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		/*
		 * Task-3: Pass the place_id inside the body of the Delete api and
		 * perform delete operation for already added place
		 */

		given().queryParam("key", "AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA")

				/*
				 * In side body we have to pass json like-{"place_id":
				 * "place ID"} as string but we store our placeid inside a
				 * variable so to resolving that we are doing like below mention
				 * way
				 */
				.body("{" + "\"place_id\": \"" + placeid + "\"" + "}").when().post("/maps/api/place/delete/json").then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}
}
