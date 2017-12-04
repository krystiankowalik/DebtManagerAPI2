package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.PayableDao;
import com.kryx07.debtmanager2.model.due.Due;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PayableService {

    private final PayableDao payableDao;

    @Autowired
    public PayableService(PayableDao payableDao) {
        this.payableDao = payableDao;
    }

    public List<Due> findAll() {
        return payableDao.findAll();
    }

    public List<Due> findAllByGroupId(int groupId) {
        return payableDao.findAllByGroup_Id(groupId);
    }

    public Due findOne(int id) {
        return payableDao.findOne(id);
    }

    public boolean exists(int id) {
        return payableDao.exists(id);
    }

    public boolean delete(int id) {
        if (!exists(id)) {
            return false;
        }
        payableDao.delete(id);
        return !exists(id);
    }

    public Due save(Due due) {
        return payableDao.save(due);

    }

    public void save(Set<Due> dues) {
        payableDao.save(dues);
    }

    public List<Due> calculatePayablesFromTransaction(final Transaction transaction) {

        List<Due> dues = new ArrayList<>();

        final BigDecimal fractionalAmount = getFractionalAmountFromTransaction(transaction);

        transaction
                .getGroup()
                .getUsers()
                .stream()
                .filter(user -> !user.equals(transaction.getPayer()))
                .forEach(debtor ->
                        dues
                                .add(new Due(
                                        debtor,
                                        transaction.getPayer(),
                                        fractionalAmount,
                                        false,
                                        transaction,
                                        transaction.getGroup())));

        return dues;

    }

    private BigDecimal getFractionalAmountFromTransaction(Transaction transaction) {

        return transaction.isCommon() ?
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size()), 10, RoundingMode.CEILING) :
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size() - 1), 10, RoundingMode.CEILING);
    }


}
