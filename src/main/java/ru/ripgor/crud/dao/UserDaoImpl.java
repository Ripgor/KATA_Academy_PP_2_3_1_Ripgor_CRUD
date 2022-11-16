package ru.ripgor.crud.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import ru.ripgor.crud.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager eManager;

    @Override
    public void saveUser(User user) {
        eManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return eManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User showUser(int id) {
        return eManager.find(User.class, id);
    }

    // Нахожу пользователя по id
    // отсоединяю
    // назначаю пользователю параметры, пришедшие из представления
    // меняю запись в БД
    @Override
    public void updateUser(int id, User user) {
        User userToUpdate = eManager.find(User.class, id);
        eManager.detach(userToUpdate);
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        eManager.merge(userToUpdate);
    }

    @Override
    public void deleteUser(int id) {
        eManager.remove(eManager.find(User.class, id));
    }
}