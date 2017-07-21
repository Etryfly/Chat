package Chat.DAO;

import Chat.Message;


import java.sql.SQLException;
import java.util.List;

/**
 * Etryfly 14.07.17.
 */

public interface MessageDao {
    Message create(Message message) throws SQLException, PersistException;
    Message read(int id) throws SQLException;
    void delete(Message message);
    List<Message> getAll() throws SQLException;
}
