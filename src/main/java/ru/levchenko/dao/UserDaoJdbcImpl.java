package ru.levchenko.dao;

import ru.levchenko.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM user_fortest";
    //language=SQL
    private final String SQL_SELECT_BY_ID = "SELECT * FROM user_fortest WHERE id = ?";
    //language=SQL
    private final String SQL_SELECT_BY_AGE = "SELECT * FROM  user_fortest WHERE age >= ? and  age <= ?";
    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO user_fortest(first_name, last_name, age) VALUES (?, ?, ?)";
    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM user_fortest WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_USER_BY_ID = "UPDATE user_fortest SET first_name=?, last_name=?, age=? WHERE id =?";
    Connection connection;

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByAge(Integer min, Integer max) {
        List<User> userList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_AGE);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getInt("id"),
                        resultSet.getInt("age"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Optional<User> find(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Integer userId = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                User user = new User(firstName, lastName, age, userId);
                return Optional.of(user);
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                User user = new User(firstName, lastName, age, id);
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getSecondName());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void update(User model, Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getSecondName());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setInt(4,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }
}
