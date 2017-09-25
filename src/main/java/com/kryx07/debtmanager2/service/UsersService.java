package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.UserDao;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public User get(int index) {
        return userDao.get(index);
    }
}
