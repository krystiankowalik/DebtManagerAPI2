package com.kryx07.debtmanager2.dao.group;

import com.kryx07.debtmanager2.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {

     List<Transaction> findAllByGroup_Id(int id);

}
