package com.kryx07.debtmanager2.dao;

import com.kryx07.debtmanager2.model.users.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    void save(User user);

    User get(int index);
}
