package com.kryx07.debtmanager2.dao.user;

import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    /*void save(User user);

    User findOne(int index);*/

    /*@Override
    List<User> findAll();*/
//
    //  @Query("SELECT * FROM user u WHERE u.username= ?1")
    User findUserByUsername(String username);

    List<User> findUsersByGroups(Group group);


}

