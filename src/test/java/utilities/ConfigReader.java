package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() throws IOException {

        prop = new Properties();

        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");

        prop.load(fis);
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getUrl() {
        return prop.getProperty("url");
    }

	public boolean isHeadless() {
		
		return Boolean.parseBoolean(prop.getProperty("headless"));
	}

	public String getExecutionMode() {
	    return prop.getProperty("execution");

	}
}