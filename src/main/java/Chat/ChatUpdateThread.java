package Chat;



import Chat.DAO.DaoFactory;
import Chat.DAO.MessageDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Etryfly 22.07.17.
 */
public class ChatUpdateThread extends Thread {
    private DaoFactory daoFactory;
    private ChatGUI gui;

    public ChatUpdateThread(DaoFactory daoFactory, ChatGUI gui) {
        this.daoFactory = daoFactory;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            Connection connection = daoFactory.getConnection();
            MessageDao messageDao = daoFactory.getMessageDao(connection);
            int lastMessageListSize = 0;
            while (true) {


                if (lastMessageListSize != messageDao.getCount()) {
                    List<Message> messageList = messageDao.getAll();
                    for (int i = lastMessageListSize; i < messageList.size() ; i++) {
                        System.out.println(messageList.get(i).getUser().getName() + " " + messageList.get(i).getData());
                        gui.addMessage(messageList.get(i));
                    }
                    lastMessageListSize = messageList.size();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
