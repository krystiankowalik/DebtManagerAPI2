package com.kryx07.debtmanager2.service;

import com.kryx07.debtmanager2.dao.GroupDao;
import com.kryx07.debtmanager2.model.users.Group;
import com.kryx07.debtmanager2.service.base.DbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupService extends DbServiceImpl<Group> {

    private final GroupDao groupDao;

    @Autowired
    public GroupService(JpaRepository<Group, Integer> dao) {
        super(dao);
        this.groupDao = (GroupDao) dao;
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
