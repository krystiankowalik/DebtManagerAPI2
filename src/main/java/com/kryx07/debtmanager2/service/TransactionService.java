package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.group.GroupDao;
import com.kryx07.debtmanager2.dao.group.TransactionDao;
import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final GroupDao groupDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao, UserDao userDao, GroupDao groupDao) {
        this.transactionDao = transactionDao;
        this.userDao = userDao;
        this.groupDao = groupDao;
    }


    public Transaction add(Transaction transaction) {
        User payer = userDao.findOne(transaction.getPayer().getId());
        Group group = groupDao.findOne(transaction.getGroup().getId());
        transaction.setGroup(group);
        transaction.setPayer(payer);
        return transactionDao.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }


}
