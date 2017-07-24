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

        MySqlDaoFactory daoFactory = new MySqlDaoFactory();
        try (Connection connection = daoFactory.getConnection()) {
            ChatGUI gui = new ChatGUI(daoFactory);
            gui.setVisible(true);

            UserDao userDao = daoFactory.getUserDao(connection);
            MessageDao messageDao = daoFactory.getMessageDao(connection);



            ChatUpdateThread chatUpdateThread = new ChatUpdateThread(daoFactory, gui);
            chatUpdateThread.start();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PersistException e) {
            e.printStackTrace();
        }

    }
}
