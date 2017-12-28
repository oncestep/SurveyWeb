package com.moyo.dao;

import com.moyo.beans.SurveyEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.sessionfactory.SF.getSession;

public class SurveyDAO {
    public void save(SurveyEntity object) {
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

    public void delete(SurveyEntity object) {
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

    public SurveyEntity findById(long id) {
        try {
            SurveyEntity object = (SurveyEntity) getSession().get(SurveyEntity.class, id);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

    public List findByProperty(String property, Object o) {
        try {
            String queryString = "from SurveyEntity as ude where ude." + property + "=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, o);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }


    public List findByBatchIdAndNaireName(Long batchId, String naireName) {
        try {
            String queryString = "from SurveyEntity as ude where ude.batchId=? and ude.naireName=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, batchId);
            queryObject.setParameter(1, naireName);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public List findAll() {
        try {
            String queryString = "from SurveyEntity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public SurveyEntity merge(SurveyEntity detachedInstance) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            SurveyEntity result = (SurveyEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(SurveyEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(SurveyEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List findByBatchId(Object o) {
        return findByProperty("batchId", o);
    }

    public List findByCreateTime(Object o) {
        return findByProperty("createTime", o);
    }

    public List findByDescription(Object o) {
        return findByProperty("description", o);
    }

    public List findByNaireName(Object o) {
        return findByProperty("naireName", o);
    }

    public List findAvailable(long batchId, long userId) {
        try {
            String queryString = "from SurveyEntity as s where s.batchId = ? " +
                    "and s.naireId != all(select a.naireId from AnswerEntity as a where a.userId = ?)";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, batchId);
            queryObject.setParameter(1, userId);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

}
