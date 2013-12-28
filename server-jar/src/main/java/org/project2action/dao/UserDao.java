package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.project2action.domain.User;

public class UserDao extends AbstractDao<User> {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findBySecurityToken(String securityToken) {
        return singleResult(criteria().add(Restrictions.eq("securityToken", securityToken)));
    }
}
