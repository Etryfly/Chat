package Chat.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Etryfly 14.07.17.
 */
public class MySqlDaoFactory implements DaoFactory {

    private static Properties dbProperties = new Properties();


    private String user;
    private String password;
    private String url;
    private String driver;

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public MessageDao getMessageDao(Connection connection) {
        return new MySqlMessageDao(connection);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return null;
    }

    public MySqlDaoFactory() {
        try {
            FileInputStream fis = new FileInputStream("src/main/java/Chat/DAO/db.properties");
            dbProperties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        user = dbProperties.getProperty("user");
        password = dbProperties.getProperty("password");
        url = dbProperties.getProperty("url");
        driver = dbProperties.getProperty("driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
