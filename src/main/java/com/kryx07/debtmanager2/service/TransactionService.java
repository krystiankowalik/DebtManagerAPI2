package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.GroupDao;
import com.kryx07.debtmanager2.dao.TransactionDao;
import com.kryx07.debtmanager2.dao.UserDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        transaction.setId(0);
        transaction.setGroup(group);
        transaction.setPayer(payer);
        return transactionDao.save(transaction);
    }

    public Transaction update(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public boolean delete(int id) {
        transactionDao.delete(id);
        return !transactionDao.exists(id);
    }

    public boolean exists(int id) {
        return transactionDao.exists(id);
    }

    public List<Transaction> findAll() {
        return transactionDao.findAll();
    }

    public Transaction findOne(int id) {
        return transactionDao.findOne(id);
    }

    public List<Transaction> findAllByGroup_Id(int id) {
        return transactionDao.findAllByGroup_Id(id);
    }

    public List<Transaction> findAllByUser_Id(int id) {
        User user = userDao.findOne(id);
        List<Transaction> usersTransactions = new ArrayList<>();
        user.getGroups()
                .forEach(g -> usersTransactions.addAll(findAllByGroup_Id(g.getId())
                .stream()
                .filter(t->t.getGroup()
                        .getUsers()
                        .contains(user))
                .collect(Collectors.toList())));
        return new ArrayList<>(usersTransactions);
    }


}
