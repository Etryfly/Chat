package Chat.DAO.MySql;

import Chat.DAO.MessageDao;
import Chat.DAO.PersistException;
import Chat.Message;
import Chat.User;

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


    public Message create(Message message) throws SQLException, PersistException {
        String sql = "INSERT INTO chat.messages (message, user) VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, message.getData());
        preparedStatement.setString(2, message.getUser().getName());
        int count = preparedStatement.executeUpdate();
        if (count != 1) {
            throw new PersistException();
        }
        return message;
    }


    public Message read(int id) throws SQLException {
        String sql = "SELECT * FROM chat.messages WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);

        ResultSet result = stm.executeQuery();
        result.next();
        Message message = new Message();
        message.setData(result.getString("message"));


        message.setUser(new User(result.getString("user")));

        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                message.setId((int) generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating message failed, no ID obtained.");
            }
        }

        return message;
    }

    @Override
    public void delete(Message message) {
        throw new UnsupportedOperationException("Coming soon");
    }


    @Override
    public List<Message> getAll() throws SQLException {
        String sql = "SELECT * FROM chat.messages;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet result = stm.executeQuery();
        List<Message> messages = new ArrayList<>();

        while (result.next()) {
            Message message = new Message();
            message.setUser(new User(result.getString("user")));
            message.setData(result.getString("message"));
            messages.add(message);
        }

        return messages;
    }
}
