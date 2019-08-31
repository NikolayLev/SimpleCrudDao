package ru.levchenko.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.levchenko.models.Car;
import ru.levchenko.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UserWithCarsDaoJDBCTemplateImpl implements UserWithCarsDao {
    private JdbcTemplate jdbcTemplate;
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT user_fortest.id, first_name ,last_name, age, car_fortest.id as car_id," +
            " model, max_speed FROM user_fortest left join car_fortest " +
            "ON user_fortest.id = car_fortest.owner_id";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT user_fortest.id, first_name ,last_name, age, car_fortest.id as car_id," +
            " model, max_speed FROM user_fortest left join car_fortest " +
            "ON user_fortest.id = car_fortest.owner_id where user_fortest.id = ?";
    //language=SQL
    private final String SQL_SELECT_BY_AGE = "SELECT user_fortest.id, first_name ,last_name, age, car_fortest.id as car_id," +
            " model, max_speed FROM user_fortest left join car_fortest " +
            "ON user_fortest.id = car_fortest.owner_id WHERE age >= ? and age <= ?";
    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO user_fortest(first_name, last_name, age) VALUES (?, ?, ?)";
    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM user_fortest WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_USER_BY_ID = "UPDATE user_fortest SET first_name=?, last_name=?, age=? WHERE id =?";
    //language=SQL
    private final String SQL_SELECT_USER_WITH_CAR = "SELECT user_fortest.id, first_name ,last_name, age, car_fortest.id as car_id," +
            " model, max_speed FROM user_fortest inner join car_fortest " +
            "ON user_fortest.id = car_fortest.owner_id";


    public UserWithCarsDaoJDBCTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAllByAge(Integer min, Integer max) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, new UsersRowMapper(), min, max);
    }

    @Override
    public Optional<User> find(Integer id) {
        List<User> userList = jdbcTemplate.query(SQL_SELECT_BY_ID, new UsersRowMapper(), id);
        if (userList!=null) {
            return Optional.of(userList.get(0));
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        return jdbcTemplate.query(SQL_SELECT_ALL, new UsersRowMapper());
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public List<User> findAllUsersWithCars() {
        List<User> userList = new ArrayList<>();
        Iterator<User> userIterator = jdbcTemplate.query(SQL_SELECT_USER_WITH_CAR, new UsersRowMapper()).iterator();
        while (userIterator.hasNext()) {
            User nextUser = userIterator.next();
            if (!userList.contains(nextUser)) {
                userList.add(nextUser);
            }
        }
        return userList;
    }
}
