package src.database;

import java.io.IOException;
import java.util.Properties;

public class DatabasePropertiesGetter {
    private static String url;
    private static String user;
    private static String password;

    protected static String getUrl() {
        return url;
    }

    protected static String getUser() {
        return user;
    }

    protected static String getPassword() {
        return password;
    }
    static {
        Properties properties = new Properties();
        try {
            properties.load(DatabasePropertiesGetter.class.getClassLoader().getResourceAsStream("application.properties"));
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
