package portal.management.edu.traning.dao.impl.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConfigFilesDataBase {

    private static final ConfigFilesDataBase instance;

    static {
        try {
            instance = new ConfigFilesDataBase();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final String url;
    private final String username;
    private final String password;

    private ConfigFilesDataBase() throws IOException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Properties props = new Properties();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("database.properties")) {

            props.load(in);

            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");

        }

    }

    public Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(url, username, password);
    }

    public static ConfigFilesDataBase getInstance() {
        return instance;
    }

}
