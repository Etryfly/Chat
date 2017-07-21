package Chat;

import Chat.DAO.MessageDao;
import Chat.DAO.MySql.MySqlDaoFactory;
import Chat.DAO.PersistException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Etryfly 21.07.17.
 */
public class Chat {

    public static void main(String[] args) {
        User user = new User("User");
        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {

            MessageDao messageDao = daoFactory.getMessageDao(connection);
            Message message = new Message();
            message.setData("testData");
            message.setUser(user);
            messageDao.create(message);

        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        }
    }
}
