import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import files.ReusableMethods;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RequestLog extends baseMethod {

	@Test
	public void xmlPost() throws IOException {

		RestAssured.baseURI = prop.getProperty("HOST");
		/*
		 * There are two types of log present in rest-assured request log and
		 * response log. 
		 * NOTE - Note that the HTTP Builder and HTTP Client may
		 * add additional headers then what's printed in the log.
		 * Here i am
		 * showing a example of request log as this one is request log i can use
		 * log() method any where after given and before when. log() method have
		 * many extension like log().all which log all the request anothere are
		 * given().log().all(). .. // Log all request specification details
		 * including parameters, headers and body given().log().params(). .. //
		 * Log only the parameters of the request given().log().body(). .. //
		 * Log only the request body given().log().headers(). .. // Log only the
		 * request headers given().log().cookies(). .. // Log only the request
		 * cookies given().log().method(). .. // Log only the request method
		 * given().log().path(). .. // Log only the request path
		 */
		Response res = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", prop.getProperty("KEY")).log().all().when().get(resources.nearBySearch()).then()
				.assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("Sydney")).and().extract().response();
		JsonPath jp = ReusableMethods.rawToJson(res);
		int count = jp.get("results.size()");

		for (int i = 0; i < count; i++) {
			String result = jp.get("results[" + i + "].name");
			System.out.println(result);
		}

	}
}
