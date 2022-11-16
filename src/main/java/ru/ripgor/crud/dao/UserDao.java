package ru.ripgor.crud.dao;

import ru.ripgor.crud.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    List<User> getAllUsers();

    User showUser(int id);

    void updateUser(int id, User user);

    void deleteUser(int id);

}
