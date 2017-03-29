package ru.steamwallet.swcommon.dao.dbimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Sergey Ignatov on 15/02/16.
 */
public abstract class SessionFactoryHolder {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected <T> void save(T obj) {
        getSession().save(obj);
    }

    protected <T> void update(T obj) {
        getSession().update(obj);
    }

    protected <T> void delete(T obj) {
        getSession().delete(obj);
    }
}
