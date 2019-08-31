package ru.levchenko.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.levchenko.models.Car;
import ru.levchenko.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UsersRowMapper implements RowMapper<User> {

    private Map<Integer, User> userList = new HashMap();

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        Integer id = resultSet.getInt("id");
        if (!userList.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Integer age = resultSet.getInt("age");
            User user = new User(firstName, lastName, age, id);
            userList.put(id, user);
        }
        if (resultSet.getInt("car_id") != 0) {
            Car car = new Car(userList.get(id), resultSet.getString("model"), resultSet.getInt("max_speed"), resultSet.getInt("car_id"));
            userList.get(id).getCarList().add(car);
        }
        return userList.get(id);
    }
}
