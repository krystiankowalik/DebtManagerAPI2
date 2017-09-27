package com.kryx07.debtmanager2.dao.group;

import com.kryx07.debtmanager2.model.users.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends JpaRepository<Group, Integer> {
}
