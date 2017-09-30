package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.group.TransactionDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }


    public Transaction add(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }


}
