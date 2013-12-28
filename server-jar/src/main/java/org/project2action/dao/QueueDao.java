package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.project2action.domain.Queue;
import org.project2action.domain.User;

import java.util.List;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.like;

public class QueueDao extends AbstractDao<Queue> {

    public QueueDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Queue> findByUser(User user) {
        return list(criteria()
                .add(eq("ownerId", user.getId())));
    }

    public List<Queue> findByName(String input) {
        return list(criteria()
            .add(like("name", "%"+input+"%")));
    }

}
