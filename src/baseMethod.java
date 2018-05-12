import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;


public class baseMethod {
	Properties prop = new Properties();

	@BeforeClass
	public void getData() throws IOException {
		
		FileInputStream fis = new FileInputStream(
				"/Users/manishmishra/Documents/workspace/API Testing/src/files/env.properties");
		
		// prop.load() method load the .property file and we have to pass properties file path.
		prop.load(fis);

	}

}
