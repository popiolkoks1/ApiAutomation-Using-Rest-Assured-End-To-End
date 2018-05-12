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

public class ResponseLog extends baseMethod {
	@Test
	public void xmlPost() throws IOException {

		RestAssured.baseURI = prop.getProperty("HOST");

		/*
		 * Here i am describing about response log. So, As we know response are
		 * taken care by then block so we can put log() method after then More
		 * Details - get("/x").then().log().statusLine(). .. // Only log the
		 * status line get("/x").then().log().headers(). .. // Only log the
		 * response headers get("/x").then().log().cookies(). .. // Only log the
		 * response cookies
		 */

		Response res = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", prop.getProperty("KEY")).when().get(resources.nearBySearch()).then().log().body()
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
