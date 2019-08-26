package ru.levchenko.dao;

import java.util.List;
import java.util.Optional;

//реализация паттерна Dao(data access object), интерфейс предоставляющий общий набор методов для взаимодействия с данными
//и не зависеть от конкретных БД
//Crud реализация 4 функций: Create, Delete, Update,Delete
public interface CrudDao<T> {
    public Optional<T> find(Integer id);
    public List<T> findAll();
    public void save(T model);
    public void update(T model, Integer id);
    public void delete(Integer id);
}
