package ru.levchenko.models;

//Класс юзер хранит информацию об пользователях и их машинах
//машины связаны с юзерами реляционными отношениями(onetomany), Один пользователь может имень несколько машин
//в postgres User хранятся с уникальными id, у машин есть внешний ключ owner_id = user.id/
public class User {
    private String firstName;
    private String secondName;
    private Integer age;
    private Integer id;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                '}';
    }

    public User() {
    }

    public User(String firstName, String secondName, Integer age, Integer id) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.id = id;
    }

    public User(String firstName, String secondName, Integer age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
