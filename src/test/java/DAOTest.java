import Chat.DAO.DaoFactory;
import Chat.DAO.MessageDao;
import Chat.DAO.MySqlDaoFactory;
import Chat.Message;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;


/**
 * Created by etryfly on 14.07.17.
 */
public class DAOTest {

    @Test
    public void testGetAll() throws Exception {
        DaoFactory daoFactory = new MySqlDaoFactory();
        List<Message> list;
        try (Connection connection = daoFactory.getConnection()) {
            MessageDao dao = daoFactory.getMessageDao(connection);
            list = dao.getAll();
        }

        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }
}
