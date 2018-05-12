import static io.restassured.RestAssured.given;

import java.io.IOException;

import files.ReusableMethods;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class xmlPostUsingReusableMethods extends baseMethod {

	@Test
	public void xmlPost() throws IOException {

		RestAssured.baseURI = prop.getProperty("HOST");
		String postBodyDate = ReusableMethods.GenerateStringFromResource(prop.getProperty("XMLFILELOCATION"));
		Response res = given().queryParam("key", prop.getProperty("KEY")).

				body(postBodyDate).when().post(prop.getProperty("POST_API_XMl_PATH")).then().assertThat()
				.statusCode(200).and().contentType(ContentType.XML).and().extract().response();
		XmlPath xp = ReusableMethods.rawToXml(res);
		String result = xp.get("PlaceAddResponse.place_id");
		System.out.println(result);

	}

}
