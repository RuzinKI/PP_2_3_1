package web.dao;

import web.entity.User;

import java.util.List;

public interface Dao {

    void add(User user);

    List<User> getAll();

    User getById(Long id);

    void delete(Long id);

    void update(User user);
}
