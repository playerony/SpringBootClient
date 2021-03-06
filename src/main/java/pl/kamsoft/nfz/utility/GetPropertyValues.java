package pl.kamsoft.nfz.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.web.client.RestClientException;

public class GetPropertyValues {
	public String get(String propertyName) {
		InputStream inputStream;
		Properties prop = new Properties();
		String propFileName = "address.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				throw new RestClientException("Some problems by lodaing a file", e);
			}
		} else {
			throw new RestClientException("property file '" + propFileName + "' not found in the classpath");
		}

		String result = prop.getProperty("address");

		return result;
	}
}
