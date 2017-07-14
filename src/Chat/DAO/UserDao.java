package Chat.DAO;

import Chat.User;

import java.util.List;

/**
 * Created by etryfly on 14.07.17.
 */
public interface UserDao {
    User create();
    User read(int id);
    void update(User user);
    void delete(User user);
    List<User> getAll();
}
