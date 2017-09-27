package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.GroupDao;
import com.kryx07.debtmanager2.model.users.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    public void save(Group group) {
        groupDao.save(group);
    }

    public Group get(int index) {
        return groupDao.getOne(index);
    }
}
