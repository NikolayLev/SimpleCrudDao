import org.junit.Test;
import ru.levchenko.dao.UserDaoJdbcImpl;
import ru.levchenko.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UserDaoJdbcImplTest {

    @Test
    public void findAllByAgeShouldReturnNewList() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("target/classes/db.properties"));
        Class.forName("org.postgresql.Driver");
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        System.out.println(dbUrl);
        System.out.println(dbUserName);
        System.out.println(dbPassword);
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl(connection);
        List<User> list = userDaoJdbc.findAllByAge(0, 100);
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void saveNewUserShouldBeInsertInTable() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("target/classes/db.properties"));
        Class.forName("org.postgresql.Driver");
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        System.out.println(dbUrl);
        System.out.println(dbUserName);
        System.out.println(dbPassword);
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl(connection);
        User user = new User("James", "Bond", 44);
        userDaoJdbc.save(user);
    }

    @Test
    public void deleteShouldRemoveUserFromDb() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("target/classes/db.properties"));
        Class.forName("org.postgresql.Driver");
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        System.out.println(dbUrl);
        System.out.println(dbUserName);
        System.out.println(dbPassword);
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl(connection);
        userDaoJdbc.delete(6);
    }

    @Test
    public void updateShouldChangeInformationInDb() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("target/classes/db.properties"));
        Class.forName("org.postgresql.Driver");
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        System.out.println(dbUrl);
        System.out.println(dbUserName);
        System.out.println(dbPassword);
        Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl(connection);
        Optional<User> user = userDaoJdbc.find(3);
        User user1= user.get();
        user1.setAge(17);
        userDaoJdbc.update(user1, 3);

    }
}
