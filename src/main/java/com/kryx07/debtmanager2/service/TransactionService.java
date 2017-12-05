package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.GroupDao;
import com.kryx07.debtmanager2.dao.TransactionDao;
import com.kryx07.debtmanager2.dao.UserDao;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.base.DbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionService extends DbServiceImpl<Transaction> {

    private final TransactionDao transactionDao;
    private final UserDao userDao;
    private final GroupDao groupDao;

    @Autowired
    public TransactionService(JpaRepository<Transaction, Integer> transactionDao, UserDao userDao, GroupDao groupDao) {
        super(transactionDao);
        this.transactionDao = (TransactionDao) transactionDao;
        this.userDao = userDao;
        this.groupDao = groupDao;
    }

    public Transaction add(Transaction transaction) {
        User payer = userDao.findOne(transaction.getPayer().getId());
        Group group = groupDao.findOne(transaction.getGroup().getId());
        transaction.setId(0);
        transaction.setGroup(group);
        transaction.setPayer(payer);
        return this.save(transaction);
    }

    public List<Transaction> findAllByGroup_Id(int id) {
        return transactionDao.findAllByGroup_Id(id);
    }

    public List<Transaction> findAllByUser_Id(int id) {
        User user = userDao.findOne(id);
        List<Transaction> usersTransactions = new ArrayList<>();
        if (user == null) {
            return null;
        }
        user.getGroups()
                .forEach(g -> usersTransactions.addAll(findAllByGroup_Id(g.getId())
                        .stream()
                        .filter(t -> t.getGroup()
                                .getUsers()
                                .contains(user))
                        .collect(Collectors.toList())));
        return new ArrayList<>(usersTransactions);
    }


}
