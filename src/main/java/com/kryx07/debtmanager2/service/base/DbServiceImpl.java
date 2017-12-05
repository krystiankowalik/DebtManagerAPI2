package com.kryx07.debtmanager2.service.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class DbServiceImpl<T> implements DbService<T> {

    private JpaRepository<T, Integer> dao;

    public DbServiceImpl(JpaRepository<T, Integer> dao) {
        this.dao = dao;
    }

    @Override
    public T save(T object) {
        return dao.save(object);
    }

    @Override
    public List<T> save(Iterable<T> objects) {
        return dao.save(objects);
    }

    @Override
    public T get(int id) {
        return dao.findOne(id);
    }

    @Override
    public List<T> getAll() {
        return dao.findAll();
    }

    @Override
    public void delete(T object) {
        dao.delete(object);
    }

    @Override
    public void delete(Iterable<T> objects) {
        dao.delete(objects);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public boolean exists(int id) {
        return dao.exists(id);
    }
}