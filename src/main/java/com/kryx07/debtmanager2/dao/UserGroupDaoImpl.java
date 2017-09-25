package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.users.UsersGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserGroupDaoImpl implements UserGroupDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserGroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(UsersGroup usersGroup) {
        sessionFactory.getCurrentSession().save(usersGroup);
    }


    @Override
    public void get(int index) {

    }
}
