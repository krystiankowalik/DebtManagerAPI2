package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.group.GroupDao;
import com.kryx07.debtmanager2.dao.group.GroupDaoCustom;
import com.kryx07.debtmanager2.model.users.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {

    private final GroupDao groupDao;
    private final GroupDaoCustom groupDaoCustom;

    @Autowired
    public GroupService(GroupDao groupDao, GroupDaoCustom groupDaoCustom) {
        this.groupDao = groupDao;
        this.groupDaoCustom = groupDaoCustom;
    }

    public void save(Group group) {
        groupDao.save(group);
    }

    public Group get(int index) {
        return groupDao.getOne(index);
    }

    public List<Group> getAllByUser(int userId) {
        return groupDaoCustom.getAllByUser(userId);

    }
}
