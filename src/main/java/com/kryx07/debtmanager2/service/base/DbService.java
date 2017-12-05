package com.kryx07.debtmanager2.service.base;

import java.util.List;

public interface DbService<T> {

    T save(T object);

    List<T> save(Iterable<T> objects);

    T get(int id);

    List<T> getAll();

    void delete(T object);

    void delete(Iterable<T> objects);

    void delete(int id);

    boolean exists(int id);




}