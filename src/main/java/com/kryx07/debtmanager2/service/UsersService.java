package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UsersService {

    private final UserDao userDao;
    private final TransactionService transactionService;

    @Autowired
    public UsersService(UserDao userDao, TransactionService transactionService) {
        this.userDao = userDao;
        this.transactionService = transactionService;
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public User findOne(int index) {
        return userDao.findOne(index);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User getByUserName(String username) {
        return userDao.findUserByUsername(username);
    }

    public boolean userExists(String username) {
        return getByUserName(username) != null;
    }

    public boolean userExists(int id) {
        return findOne(id) != null;
    }

    public List<User> getByGroupId(Group group) {
        return userDao.findUsersByGroups(group);
    }

    public boolean delete(int id) {
        userDao.delete(id);
        return !userExists(id);
    }



}
