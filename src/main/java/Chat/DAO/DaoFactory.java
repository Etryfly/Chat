package Chat.DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by etryfly on 14.07.17.
 */
public interface DaoFactory {
    Connection getConnection() throws SQLException;
    MessageDao getMessageDao(Connection connection);
    UserDao getUserDao(Connection connection);
}
