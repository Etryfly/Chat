package Chat.DAO.MySql;

import Chat.DAO.PersistException;
import Chat.DAO.UserDao;
import Chat.Message;
import Chat.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public User create(String name) throws SQLException, PersistException {
        String sql = "INSERT INTO chat.users (name) VALUES ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        int count = preparedStatement.executeUpdate();
        if (count != 1) {
            throw new PersistException();
        }

        User user = new User(name);

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user.setId((int) generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return user;
    }

    @Override
    public User read(int id) throws SQLException {
        String sql = "SELECT * FROM chat.users WHERE id = ?;";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, id);

        ResultSet result = stm.executeQuery();
        result.next();
        User user = new User(result.getString("name"));

        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE chat.users SET name=? WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chat.users WHERE id=?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM chat.users;";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet result = stm.executeQuery();
        List<User> users = new ArrayList<>();

        while (result.next()) {
            User user = new User(result.getString("name"));
            user.setId(result.getInt("id"));
            users.add(user);
        }

        return users;
    }
}
