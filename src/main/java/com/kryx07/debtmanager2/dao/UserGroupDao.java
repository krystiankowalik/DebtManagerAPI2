package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.users.UsersGroup;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupDao {

    void save(UsersGroup usersGroup);

    void get(int index);
}
