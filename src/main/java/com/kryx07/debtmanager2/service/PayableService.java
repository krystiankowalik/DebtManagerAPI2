package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.PayableDao;
import com.kryx07.debtmanager2.model.payable.Payable;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
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

    public List<Payable> findAll() {
        return payableDao.findAll();
    }

    public List<Payable> findAllByGroupId(int groupId) {
        return payableDao.findAllByGroup_Id(groupId);
    }

    public Payable findOne(int id) {
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

    public Payable save(Payable payable) {
        return payableDao.save(payable);

    }

    public Set<Payable> calculatePayablesFromTransaction(final Transaction transaction) {

        Set<Payable> payables = new HashSet<>();

        final BigDecimal fractionalAmount = getFractionalAmountFromTransaction(transaction);

        transaction
                .getGroup()
                .getUsers()
                .stream()
                .filter(user -> !user.equals(transaction.getPayer()))
                .forEach(debtor ->
                        payables
                                .add(new Payable(transaction.getPayer(),
                                        debtor,
                                        fractionalAmount,
                                        false,
                                        transaction,
                                        transaction.getGroup())));

        return payables;

    }

    private BigDecimal getFractionalAmountFromTransaction(Transaction transaction) {

        return transaction.isCommon() ?
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size()), 10, RoundingMode.CEILING) :
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size() - 1), 10, RoundingMode.CEILING);
    }


}
