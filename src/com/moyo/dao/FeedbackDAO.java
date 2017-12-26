package com.moyo.dao;

import com.moyo.beans.FeedbackEntity;
import com.moyo.beans.FeedbackEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.sessionfactory.SF.getSession;

public class FeedbackDAO {
    public void save(FeedbackEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.save(object);
            transaction.commit();
            session.clear();
            session.close();
        }catch (Exception e){
            throw e;
        }
    }
    public void delete(FeedbackEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.delete(object);
            transaction.commit();
            session.clear();
            session.close();
        }catch (Exception e){
            throw e;
        }
    }
    public FeedbackEntity findById(long id){
        try{
            FeedbackEntity object=(FeedbackEntity) getSession().get(FeedbackEntity.class,id);
            return object;
        }catch (Exception e){
            throw e;
        }
    }
    public List findByProperty(String property, Object o){
        try{
            String queryString="from FeedbackEntity as ude where ude."+property+"=?";
            Query queryObject=getSession().createQuery(queryString);
            queryObject.setParameter(0,o);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List findAll(){
        try{
            String queryString = "from FeedbackEntity";
            Query queryObject=getSession().createQuery(queryString);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public FeedbackEntity merge(FeedbackEntity detachedInstance) {
        try {
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            FeedbackEntity result = (FeedbackEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(FeedbackEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(FeedbackEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List findByFeedbacks(Object o){
        return findByProperty("feedbacks",o);
    }
    public List findByNaireId(Object o){
        return findByProperty("naireId",o);
    }
    public List findByUserId(Object o){
        return findByProperty("userId",o);
    }
}
