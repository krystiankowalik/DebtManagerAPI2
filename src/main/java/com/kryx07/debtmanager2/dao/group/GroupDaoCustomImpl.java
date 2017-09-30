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
        /*Query query = sessionFactory.getCurrentSession().createQuery(
                "select g.id, g.password, g.username, g.groups from User users join Group.users g where user.id=:user_id");
        query.setParameter("user_id", userId);
        return query.list();*/


        /*String sqlQuery = "SELECT g.group_id, g.name FROM groups g JOIN users_groups WHERE users_groups.user_id =  " + userId + " LIMIT 1 Distinct";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);

        query.addEntity(Group.class);

        return query.list();*/

        Query query = sessionFactory.getCurrentSession().createQuery(
                "from User u inner  join Group g where u.id=g.users.id and u.id= " + userId
        );
        return query.list();

    }


}
