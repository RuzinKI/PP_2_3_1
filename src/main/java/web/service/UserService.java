package web.service;

import web.entity.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> getAll();

    User getById(Long id);

    void delete(User user);

    void update(User user);
}
