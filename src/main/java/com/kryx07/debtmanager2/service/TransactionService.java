package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.group.TransactionDao;
import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
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

    @Autowired
    public TransactionService(TransactionDao transactionDao, UserDao userDao) {
        this.transactionDao = transactionDao;
        this.userDao = userDao;
    }


    public Transaction add(Transaction transaction) {
        User payer = userDao.findOne(transaction.getPayer().getId());
        transaction.setPayer(payer);
        return transactionDao.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }


}
