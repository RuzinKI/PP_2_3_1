package web.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PrePersist;
import java.util.List;

@Repository
public class UserDao implements Dao<Long, User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = entityManager.createQuery("SELECT u FROM User u ", User.class).getResultList();
        return users;
    }

    @Override
    public User getById(Long userId) {
        User user = entityManager.find(User.class, userId);
        return user;
    }

    @Override
    public void delete(User user) {
        entityManager.merge(user);
    }

    @Override
    public void update(User user) {
        entityManager.remove(user);
    }
}
