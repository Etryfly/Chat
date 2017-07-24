package Chat;

import Chat.DAO.MessageDao;
import Chat.DAO.MySql.MySqlDaoFactory;
import Chat.DAO.PersistException;
import Chat.DAO.UserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Etryfly 21.07.17.
 */
public class Chat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            ChatGUI gui = new ChatGUI();
            gui.setVisible(true);

            UserDao userDao = daoFactory.getUserDao(connection);
            MessageDao messageDao = daoFactory.getMessageDao(connection);

            User user = userDao.create(name);

            ChatUpdateThread chatUpdateThread = new ChatUpdateThread(daoFactory, gui);
            chatUpdateThread.start();

            while (true) {
                String messageData = scanner.nextLine();
                Message message = new Message();
                message.setData(messageData);
                message.setUser(user);
                messageDao.create(message);
            }
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        }

    }
}
