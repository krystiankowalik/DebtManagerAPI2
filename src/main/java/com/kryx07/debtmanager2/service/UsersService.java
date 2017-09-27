package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService {

    private final UserDao userDao;

    @Autowired
    public UsersService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User get(int index) {
        return userDao.getOne(index);
    }

}
