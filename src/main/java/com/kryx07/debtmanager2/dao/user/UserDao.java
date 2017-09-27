package com.kryx07.debtmanager2.dao.user;

import com.kryx07.debtmanager2.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /*void save(User user);

    User get(int index);*/
}
