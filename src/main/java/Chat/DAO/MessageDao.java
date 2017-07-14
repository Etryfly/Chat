package Chat.DAO;

import Chat.Message;
import Chat.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by etryfly on 14.07.17.
 */
public interface MessageDao {
    Message create();
    Message read(int id) throws SQLException;
    void update(Message message);
    void delete(Message message);
    List<Message> getAll() throws SQLException;
}
