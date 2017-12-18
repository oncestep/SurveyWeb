package com.moyo.DAO;

import com.moyo.Beans.QuestionEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.SessionFactory.SF.getSession;

public class QuestionDAO {
    public void save(QuestionEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.save(object);
            transaction.commit();
        }catch (Exception e){
            throw e;
        }
    }
    public void delete(QuestionEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }catch (Exception e){
            throw e;
        }
    }
    public QuestionEntity findById(String id){
        try{
            QuestionEntity object=(QuestionEntity) getSession().get(QuestionEntity.class,id);
            return object;
        }catch (Exception e){
            throw e;
        }
    }
    public List findByProperty(String property, Object o){
        try{
            String queryString="from QuestionEntity as ude where ude."+property+"=?";
            Query queryObject=getSession().createQuery(queryString);
            queryObject.setParameter(0,o);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List findAll(){
        try{
            String queryString = "from QuestionEntity";
            Query queryObject=getSession().createQuery(queryString);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public QuestionEntity merge(QuestionEntity detachedInstance) {
        try {
            QuestionEntity result = (QuestionEntity) getSession().merge(detachedInstance);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(QuestionEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(QuestionEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List findByContent(Object o){
        return findByProperty("content",o);
    }
    public List findByNaireId(Object o){
        return findByProperty("naireId",o);
    }
    public List findByOrder(Object o){
        return findByProperty("order",o);
    }
    public List findByType(Object o){
        return findByProperty("type",o);
    }
}
