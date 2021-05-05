package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static final String URL_SCHEME = "url.scheme";
    public static final String URL_HOST = "url.host";
    public static final String URL_RUN_ID = "url.run.id";

    public static final String USER_TOKEN = "user.token";

    public static final String FILE_INPUT = "file.input";
    public static final String FILE_OUTPUT = "file.output";

    private static final Properties PROPERTIES;
    private static final String CONFIG_DEFAULT_PATH = "src/main/resources/config.properties";
    private static final String SECURE_CONFIG_DEFAULT_PATH = "src/main/resources/my-config.properties";

    static {
        PROPERTIES = initPropertyData(CONFIG_DEFAULT_PATH);
        if (new File(SECURE_CONFIG_DEFAULT_PATH).exists()) {
            String token = initPropertyData(SECURE_CONFIG_DEFAULT_PATH).getProperty(USER_TOKEN);
            PROPERTIES.setProperty(USER_TOKEN, token);
        }
    }

    public static String getPropertyData(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static Properties initPropertyData(String fileName) {
        Properties prop = null;
        try (var fis = new FileInputStream(fileName)) {
            prop = new Properties();
            prop.load(fis);
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        return prop;
    }
}
