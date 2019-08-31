package ru.levchenko.dao;

import ru.levchenko.models.Car;
import ru.levchenko.models.User;

import java.util.List;

public interface UserWithCarsDao extends UserDao {
    public List<Car> findAllCars();
    public List<User> findAllUsersWithCars();
}
