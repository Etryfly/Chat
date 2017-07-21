package Chat;

import Chat.DAO.MessageDao;
import Chat.DAO.MySql.MySqlDaoFactory;
import Chat.DAO.MySql.MySqlMessageDao;
import Chat.DAO.MySql.MySqlUserDao;
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
            UserDao userDao = daoFactory.getUserDao(connection);
            MessageDao messageDao = daoFactory.getMessageDao(connection);

            User user = userDao.create(name);
            System.out.println(user.getId() + " " + user.getName());
        } catch (SQLException | PersistException e) {
            e.printStackTrace();
        }

    }
}
