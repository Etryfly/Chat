package Chat.DAO;

import Chat.MessageDao;
import Chat.UserDao;

import java.sql.Connection;

/**
 * Created by etryfly on 14.07.17.
 */
public interface DaoFactory {
    Connection getConnection();
    MessageDao getMessageDao(Connection connection);
    UserDao getUserDao(Connection connection);
}
