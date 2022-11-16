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
    public void save(User user) {
        eManager.persist(user);
    }

    @Override
    public List<User> index() {
        return eManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        return eManager.find(User.class, id);
    }

    // Нахожу пользователя по id
    // отсоединяю
    // назначаю пользователю параметры, пришедшие из представления
    // меняю запись в БД
    @Override
    public void update(int id, User user) {
        User userToUpdate = eManager.find(User.class, id);
        eManager.detach(userToUpdate);
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        eManager.merge(userToUpdate);
    }

    @Override
    public void delete(int id) {
        eManager.remove(eManager.find(User.class, id));
    }
}