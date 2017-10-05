package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.payable.Payable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayableDao extends JpaRepository<Payable, Integer> {

    List<Payable> findAllByGroup_Id(int group_id);
    /*List<Payable> findAllByDebtor_Id(int debtor_id);
    List<Payable> findAllByCreditor_Id(int creditor_id);
*/

}
