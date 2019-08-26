package ru.levchenko.dao;

import ru.levchenko.models.User;

import java.util.List;

//интерфейс UserDao наследует CrudDao и дополняет его методом findAllByAge
public interface UserDao extends CrudDao<User> {
    List<User> findAllByAge(Integer min, Integer max);
}
