import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class xmlPostRequest {

	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

	@Test
	public void xmlPost() throws IOException {

		RestAssured.baseURI = "https://maps.googleapis.com/";
		String postBodyDate = GenerateStringFromResource(
				"/Users/manishmishra/Documents/workspace/API Testing/src/files/googleApiBody.xml");

		Response res = given().queryParam("key", "AIzaSyAPpR0HrN6YvfSORATAIVKf81cOh8zDYjA").

				body(postBodyDate).when().post("/maps/api/place/add/xml").then().assertThat().statusCode(200).and()
				.contentType(ContentType.XML).and().extract().response();

		String response = res.asString();
		System.out.println(response);
		XmlPath xp = new XmlPath(response);
		String result = xp.get("PlaceAddResponse.place_id");
		System.out.println(result);

	}

}
