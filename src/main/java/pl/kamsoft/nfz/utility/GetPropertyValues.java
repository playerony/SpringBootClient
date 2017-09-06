package pl.kamsoft.nfz.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValues {
	public String get(String propertyName) throws IOException {
		InputStream inputStream;
		Properties prop = new Properties();
		String propFileName = "address.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}

		String result = prop.getProperty("address");

		return result;
	}
}
