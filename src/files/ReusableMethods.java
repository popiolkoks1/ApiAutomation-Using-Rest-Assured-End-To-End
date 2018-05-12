package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXml(Response r) {
		String response = r.asString();
		XmlPath xp = new XmlPath(response);

		return xp;
	}

	public static JsonPath rawToJson(Response r) {
		String response = r.asString();
		JsonPath jp = new JsonPath(response);

		return jp;
	}

	/*
	 * Problem - Create POST request with complex XML body Solution - I keep my
	 * bodies in the resources directory, and read them into a string using the
	 * following method . Basically this method read xml and convert it into
	 * string
	 */
	public static String GenerateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}

}
