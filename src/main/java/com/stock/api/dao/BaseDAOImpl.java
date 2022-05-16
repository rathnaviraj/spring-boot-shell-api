package com.stock.api.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BaseDAOImpl implements BaseDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public <T> T findById(long id, Class<T> tClass) {
        return getSession().get(tClass, id);
    }

    @Override
    public <T> void save(final T t) {
        getSession().persist(t);
    }

    @Override
    public <T> void update(final T t) {
        getSession().update(t);
    }

    @Override
    public <T> void delete(final T t) {
        getSession().delete(t);
    }
}
