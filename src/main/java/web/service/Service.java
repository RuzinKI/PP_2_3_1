package web.service;

import java.util.List;

public interface Service<K, E> {

    void add(E e);

    List<E> getAll();

    E getById(K k);

    void delete(E e);

    void update(E e);
}
