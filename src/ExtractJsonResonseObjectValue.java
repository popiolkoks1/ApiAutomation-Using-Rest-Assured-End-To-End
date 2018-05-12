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

public class ExtractJsonResonseObjectValue extends baseMethod {

	@Test
	public void xmlPost() throws IOException {

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res = given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", prop.getProperty("KEY")).when().get(resources.nearBySearch()).then().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and().body("results[0].name", equalTo("Sydney"))
				.and().extract().response();
		// 1st Way of Doing
		JsonPath jp = ReusableMethods.rawToJson(res);
		int count = jp.get("results.size()");
            for (int i = 0; i < count; i++) {
			String result = jp.get("results[" + i + "].name");
			System.out.println(result);
		}

		/* 2nd Way of Doing..
		 * JsonPath jp = res.jsonPath(); List<Map<String, Object>> resultsList =
		 * jp.getList("results"); for(Map<String, Object> result: resultsList) {
		 * System.out.println(result.get("name")); }
		 */

	}
}
