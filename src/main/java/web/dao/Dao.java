package web.dao;

import java.util.List;

public interface Dao<K,E> {

    void add(E e);

    List<E> getAll();

    E getById(K k);

    void delete(E e);

    void update(E e);
}
