package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.due.Due;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DueDao extends JpaRepository<Due, Integer> {

    List<Due> findAllByGroup_Id(int group_id);
    /*List<Due> findAllByDebtor_Id(int debtor_id);
    List<Due> findAllByCreditor_Id(int creditor_id);
*/

}
