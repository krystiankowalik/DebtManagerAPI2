package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService {

    private final UserDao userDao;

    @Autowired
    public UsersService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public User get(int index) {
        return userDao.findOne(index);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public User getByUserName(String username) {
        return userDao.findUserByUsername(username);
    }

    public boolean userExists(String username) {
        return getByUserName(username) != null;
    }

    public boolean userExists(int id) {
        return get(id) != null;
    }

    public List<User> getByGroupId(Group group) {
        return userDao.findUsersByGroups(group);
    }

    public boolean delete(int id) {
        userDao.delete(id);
        return !userExists(id);
    }

}
