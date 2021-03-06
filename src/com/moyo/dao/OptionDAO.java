package com.moyo.dao;

import com.moyo.beans.OptionEntity;

import org.hibernate.*;


import java.util.List;

import static com.moyo.sessionfactory.SF.getSession;


public class OptionDAO {

    public void save(OptionEntity object) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();

            session.save(object);
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(OptionEntity object) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();

            session.delete(object);
            transaction.commit();
            session.clear();
            session.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public OptionEntity findById(long id) {
        try {
            OptionEntity object = (OptionEntity) getSession().get(OptionEntity.class, id);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

    public List findByProperty(String property, Object o) {
        try {
            String queryString = "from OptionEntity as ude where ude." + property + "=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, o);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public List findAll() {
        try {
            String queryString = "from OptionEntity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public OptionEntity merge(OptionEntity detachedInstance) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();

            OptionEntity result = (OptionEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(OptionEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(OptionEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List findByContent(Object o) {
        return findByProperty("content", o);
    }

    public List findByQuestionId(Object o) {
        return findByProperty("questionId", o);
    }


    /*  选项被选次数+1  */
    public void addHits(long optId) {
        String queryString = "update OptionEntity as o set o.hits = o.hits + 1 where optionId = ?";
        Query queryObject = getSession().createQuery(queryString);
        queryObject.setParameter(0,optId);
        queryObject.executeUpdate();
    }
}
