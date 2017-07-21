package Chat.DAO;

import Chat.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by etryfly on 14.07.17.
 */
public interface UserDao {
    User create(String name) throws SQLException, PersistException;
    User read(int id) throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
    List<User> getAll() throws SQLException;
}
