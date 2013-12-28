package org.project2action.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.LongType;
import org.project2action.domain.Person;
import org.project2action.domain.User;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;
import static org.hibernate.criterion.Restrictions.eq;

public class PersonDao extends AbstractDao<Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Person> findByLabel(String label) {
        List<Long> ids = listIds(sql("select l.ticket_id ID from LABELS l where l.label = :label ")
                .addScalar("ID", LongType.INSTANCE)
                .setParameter("label", label));

        if (isEmpty(ids)) return Collections.EMPTY_LIST;
        return list(criteria().add(Restrictions.in("id", ids)));
    }

    public List<Person> findByQueue(User user, Long queueId) {
        return list(criteria()
                .add(eq("queueId", queueId))
                .add(eq("userId", user.getId()))
        );
    }

    public void delete(User user, Person person) {

    }
}
