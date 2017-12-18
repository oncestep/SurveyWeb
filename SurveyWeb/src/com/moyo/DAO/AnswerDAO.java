package com.moyo.DAO;

import com.moyo.Beans.AnswerEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.SessionFactory.SF.getSession;

public class AnswerDAO {
    public void save(AnswerEntity object){
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
    public void delete(AnswerEntity object){
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
    public AnswerEntity findById(String id){
        try{
            AnswerEntity object=(AnswerEntity) getSession().get(AnswerEntity.class,id);
            return object;
        }catch (Exception e){
            throw e;
        }
    }
    public List findByProperty(String property, Object o){
        try{
            String queryString="from AnswerEntity as ude where ude."+property+"=?";
            Query queryObject=getSession().createQuery(queryString);
            queryObject.setParameter(0,o);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List findAll(){
        try{
            String queryString = "from AnswerEntity";
            Query queryObject=getSession().createQuery(queryString);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public AnswerEntity merge(AnswerEntity detachedInstance) {
        try {
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            AnswerEntity result = (AnswerEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(AnswerEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(AnswerEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List findByAnswerTime(Object o){
        return findByProperty("answerTime",o);
    }
    public List findByNaireId(Object o){
        return findByProperty("naireId",o);
    }
    public List findByUserId(Object o){
        return findByProperty("userId",o);
    }
}
