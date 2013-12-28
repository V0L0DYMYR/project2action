package org.project2action.dao;

import com.yammer.dropwizard.util.Generics;
import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class AbstractDao<E> {

    private Logger LOG =  LoggerFactory.getLogger(getClass());
    private final SessionFactory sessionFactory;
    private final Class<?> entityClass;

    public AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = checkNotNull(sessionFactory);
        this.entityClass = Generics.getTypeParameter(getClass());
    }

    protected Session session() {
        Session session = sessionFactory.getCurrentSession();
        LOG.debug("current session:" + session.hashCode());
        return session;
    }

    protected Criteria criteria() {
        return session().createCriteria(entityClass, entityClass.getSimpleName());
    }

    protected Criteria criteria(Class<?> clazz) {
        return session().createCriteria(clazz);
    }

    protected Query namedQuery(String queryName) throws HibernateException {
        return session().getNamedQuery(checkNotNull(queryName));
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) entityClass;
    }

    @SuppressWarnings("unchecked")
    public String getEntityName() {
        return getEntityClass().getSimpleName();
    }

    @SuppressWarnings("unchecked")
    protected E singleResult(Criteria criteria) throws HibernateException {
        return (E) checkNotNull(criteria).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected E singleResult(Query query) throws HibernateException {
        return (E) checkNotNull(query).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    protected List<E> list(Criteria criteria) throws HibernateException {
        return checkNotNull(criteria).list();
    }

    @SuppressWarnings("unchecked")
    protected List<E> list(Query query) throws HibernateException {
        return checkNotNull(query).list();
    }

    @SuppressWarnings("unchecked")
    protected List<Long> listIds(Query query) throws HibernateException {
        return checkNotNull(query).list();
    }

    @SuppressWarnings("unchecked")
    public E get(Serializable id) {
        return (E) session().get(entityClass, checkNotNull(id));
    }

    @SuppressWarnings("unchecked")
    protected void execute(Query query) {
        checkNotNull(query).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    protected Query query(String s) {
        return session().createQuery(checkNotNull(s));
    }

    protected SQLQuery sql(String sql){
        return session().createSQLQuery(sql);
    }

    public E merge(E entity){
        return (E) session().merge(entity);
    }

    public E saveOrUpdate(E entity) throws HibernateException {
        session().saveOrUpdate(checkNotNull(entity));
        return entity;
    }

    public List<E> findAll() {
        return list(criteria());
    }

    public void delete(Long id) {
        execute(query("delete from " + getEntityName() + " e where e.id = :id")
                .setLong("id", id));
    }

    protected <T> T initialize(T proxy) throws HibernateException {
        if (!Hibernate.isInitialized(proxy)) {
            Hibernate.initialize(proxy);
        }
        return proxy;
    }
}
