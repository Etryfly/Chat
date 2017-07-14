package Chat.DAO;

import java.sql.Connection;

/**
 * Created by etryfly on 14.07.17.
 */
public class MySqlDaoFactory implements DaoFactory {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public MessageDao getMessageDao(Connection connection) {
        return null;
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return null;
    }
}
