package Chat.DAO;

import Chat.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Etryfly 14.07.17.
 */
public class MySqlMessageDao implements MessageDao {
    private final Connection connection;

    public MySqlMessageDao(Connection connection) {
        this.connection = connection;
    }




    @Override
    public Message create() {
        //INSERT INTO `chat`.`messages` (`id`, `message`, `user`) VALUES ('1', 'test', 'test');
        return null;
    }


    public Message read(int id) throws SQLException {
        String sql = "SELECT * FROM chat.messages WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);

        ResultSet result = stm.executeQuery();
        result.next();
        Message message = new Message();
        message.setData(result.getString("message"));
        message.setUser(result.getString("user"));

        return message;
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(Message message) {

    }


    @Override
    public List<Message> getAll() throws SQLException {
        String sql = "SELECT * FROM chat.messages;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet result = stm.executeQuery();
        List<Message> messages = new ArrayList<>();

        while (result.next()) {
            Message message = new Message();
            message.setUser(result.getString("user"));
            message.setData(result.getString("message"));
            messages.add(message);
        }

        return messages;

    }
}
