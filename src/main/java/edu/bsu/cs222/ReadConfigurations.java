package edu.bsu.cs222;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigurations {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final String API_KEY_PROPERTY = "api.key";

    public static String getApiKey() {
        Properties properties = new Properties();
        try (InputStream input = ReadConfigurations.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input == null) {
                System.err.println("Unable to find " + CONFIG_FILE_PATH);
                return null;
            }

            properties.load(input);
            return properties.getProperty(API_KEY_PROPERTY);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}