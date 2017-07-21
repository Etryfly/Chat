package Chat.DAO.MySql;

import Chat.DAO.UserDao;
import Chat.User;

import java.sql.Connection;
import java.util.List;

/**
 * Etryfly 21.07.17.
 */
public class MySqlUserDao implements UserDao {

    private final Connection connection;

    public MySqlUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create() {
        return null;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
