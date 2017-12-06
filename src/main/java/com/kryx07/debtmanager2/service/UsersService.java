package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.UserDao;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.base.DbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService extends DbServiceImpl<User> {

    private final UserDao userDao;
    private final TransactionService transactionService;

    @Autowired
    public UsersService(JpaRepository<User, Integer> userDao, TransactionService transactionService) {
        super(userDao);
        this.userDao = (UserDao) userDao;
        this.transactionService = transactionService;
    }

    public List<User> getByGroupId(Group group) {
        return userDao.findUsersByGroups(group);
    }


}
