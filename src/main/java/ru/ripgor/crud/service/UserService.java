package ru.ripgor.crud.service;

import ru.ripgor.crud.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> index();

    User show(int id);

    void update(int id, User user);

    void delete(int id);
}
