package Chat;

import Chat.DAO.DaoFactory;
import Chat.DAO.MessageDao;
import Chat.DAO.PersistException;
import Chat.DAO.UserDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Etryfly 24.07.17.
 */
public class ChatGUI extends JFrame {
    private JTextArea jTextArea = new JTextArea(1, 1);
    private JTextField jTextField = new JTextField("Enter message", 1);
    private JButton jButton = new JButton("Send");
    private JScrollPane jScrollPane = new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
   // private JOptionPane jOptionPane = new JOptionPane();

    private DaoFactory daoFactory;
    private Connection connection;
    private MessageDao messageDao;
    private UserDao userDao;

    private User user;

    public ChatGUI(DaoFactory daoFactory) throws SQLException, PersistException {
        super("Chat");

        this.daoFactory = daoFactory;
        connection = daoFactory.getConnection();
        messageDao = daoFactory.getMessageDao(connection);
        userDao = daoFactory.getUserDao(connection);

        setBounds(100, 100, 650, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 2.0;
        c.weightx = 2.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        container.add(jTextField, c);

        c.weighty = 2.0;
        c.weightx = 2.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        //c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        jTextArea.setEditable(false);
//        jTextArea.append("qweqweqwe\n\n\n\n\n");
//        jTextArea.append("qweqweqweqweqweqwe\n\n\n\n\nqweqweqwe\n\n\n\n\nqweqweqwe\n\n\n\n\n");
//        jTextArea.append("qweqweqwe");
//        jTextArea.append("test");
        jScrollPane.setPreferredSize(new Dimension(40,getHeight() - jTextField.getHeight() - 25));
        container.add(jScrollPane, c);

        c.weightx = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = -5;
        jButton.addActionListener(new SendActionListener());
        container.add(jButton, c);

        String name = askName();
        user = userDao.create(name);
    }

    public void addMessage(Message message) {
        jTextArea.append(message.getUser().getName() + ": " + message.getData() + "\n");
    }

    public String askName() {
        return (String)JOptionPane.showInputDialog(
                this,
                "Введите свое имя",
                "Enter you name",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "%username%");
    }

    public class SendActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Message message = new Message();
            message.setUser(user);
            message.setData(jTextField.getText());
            try {
                messageDao.create(message);
                jTextField.setText("");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (PersistException e) {
                e.printStackTrace();
            }

        }
    }
}
