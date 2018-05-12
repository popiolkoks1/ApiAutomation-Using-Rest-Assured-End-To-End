import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import files.resources;
import files.payLoad;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class dataDrivenCode {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"/Users/manishmishra/Documents/workspace/API Testing/src/files/env.properties");
		prop.load(fis);

	}

	@Test
	public void optimizeCode() {

		RestAssured.baseURI = prop.getProperty("HOST");

		Response firstApiResult = given().queryParam("key", prop.getProperty("KEY")).body(payLoad.getPostData()).when()
				.post(resources.placeAddData()).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.and().body("status", equalTo("OK")).extract().response();

		String responseString = firstApiResult.asString();
		System.out.println(responseString);

		JsonPath js = new JsonPath(responseString);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		given().queryParam("key", prop.getProperty("KEY")).body("{" + "\"place_id\": \"" + placeid + "\"" + "}").when()
				.post(resources.placeDeleteData()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));

	}

}
