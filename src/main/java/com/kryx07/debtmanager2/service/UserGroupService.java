package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.UserGroupDao;
import com.kryx07.debtmanager2.model.users.UsersGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupDao userGroupDao;

    public void save(UsersGroup usersGroup) {
        userGroupDao.save(usersGroup);
    }
}
