package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.project2action.domain.Idea;

import java.util.List;

import static org.hibernate.criterion.Restrictions.like;

public class IdeaDao extends AbstractDao<Idea> {

    public IdeaDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Idea> find(String query) {
        return list(criteria()
                .add(like("name", "%" + query + "%")));
    }


}
