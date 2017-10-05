package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.GroupDao;
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

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group save(Group group) {
        return groupDao.save(group);
    }

    public Group get(int index) {
        return groupDao.findOne(index);

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

    public boolean delete(int id) {
        groupDao.delete(id);
        return get(id) == null;
    }

    public boolean exists(int id){
        return groupDao.exists(id);
    }

    public Group findOne(int id) {
        return groupDao.findOne(id);
    }
}
