package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.users.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(User user) {
//        sessionFactory.getCurrentSession().beginTransaction();
        sessionFactory.getCurrentSession().save(user);
//        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    @Transactional
    public User get(int index) {
        return (User) sessionFactory.getCurrentSession().get(User.class, index);
    }
}
