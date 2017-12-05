package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.DueDao;
import com.kryx07.debtmanager2.model.due.Due;
import com.kryx07.debtmanager2.model.transaction.Transaction;
import com.kryx07.debtmanager2.model.transaction.TransactionType;
import com.kryx07.debtmanager2.model.users.User;
import com.kryx07.debtmanager2.service.base.DbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DueService extends DbServiceImpl<Due> {

    private final DueDao dueDao;

    @Autowired
    public DueService(JpaRepository<Due, Integer> dueDao) {
        super(dueDao);
        this.dueDao = (DueDao) dueDao;
    }

    public List<Due> findAllByGroupId(int groupId) {
        return dueDao.findAllByGroup_Id(groupId);
    }

    public List<Due> calculatePayablesFromTransaction(final Transaction transaction) {
        return transaction
                .getGroup()
                .getUsers()
                .stream()
                .filter(user -> !user.equals(transaction.getPayer()))
                .map(user -> {
                    User debtor;
                    User creditor;
                    if (transaction.getTransactionType().equals(TransactionType.INCOME)) {
                        debtor = transaction.getPayer();
                        creditor = user;
                    } else {
                        debtor = user;
                        creditor = transaction.getPayer();
                    }
                    return new Due(debtor,
                            creditor,
                            getFractionalAmountFromTransaction(transaction),
                            false,
                            transaction,
                            transaction.getGroup()
                    );
                })
                .collect(Collectors.toList());
    }

    private BigDecimal getFractionalAmountFromTransaction(Transaction transaction) {

        return transaction.isCommon() ?
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size()), 10, RoundingMode.CEILING) :
                transaction.getAmount()
                        .divide(BigDecimal.valueOf(transaction.getGroup().getUsers().size() - 1), 10, RoundingMode.CEILING);
    }

}
