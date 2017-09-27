package com.kryx07.debtmanager2.dao.group;

import com.kryx07.debtmanager2.model.users.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GroupDaoCustom {

    List<Group> getAllByUser(int userId);
}
