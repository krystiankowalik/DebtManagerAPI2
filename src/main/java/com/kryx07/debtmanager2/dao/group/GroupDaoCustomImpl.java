package com.kryx07.debtmanager2.dao.group;

import com.kryx07.debtmanager2.model.users.Group;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoCustomImpl implements GroupDaoCustom {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDaoCustomImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Group> getAllByUser(int userId) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "from Group.users " +
                        "where User.id=:user_id");
        query.setParameter("user_id", userId);
        return query.list();
    }
}
