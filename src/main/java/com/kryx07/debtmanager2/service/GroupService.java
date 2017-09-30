package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.group.GroupDao;
import com.kryx07.debtmanager2.dao.group.GroupDaoCustom;
import com.kryx07.debtmanager2.dao.user.UserDao;
import com.kryx07.debtmanager2.model.users.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService {

    private final GroupDao groupDao;
    private final GroupDaoCustom groupDaoCustom;
    private final UserDao userDao;

    @Autowired
    public GroupService(GroupDao groupDao, GroupDaoCustom groupDaoCustom, UserDao userDao) {
        this.groupDao = groupDao;
        this.groupDaoCustom = groupDaoCustom;
        this.userDao = userDao;
    }

    public Group save(Group group) {
        return groupDao.save(group);
    }

    public Group get(int index) {
        return groupDao.getOne(index);

    }

    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }


    public List<Group> getAllByUserId(int userId) {
        return groupDao.findAll()
                .stream()
                .filter(g -> g.getUsers()
                        .stream()
                        .anyMatch(user -> user
                                .getId() == userId))
                .collect(Collectors.toList());
    }
}
