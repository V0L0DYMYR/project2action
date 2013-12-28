package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.project2action.domain.Poll;

public class PollDao extends AbstractDao<Poll> {

    public PollDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
