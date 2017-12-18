package com.moyo.DAO;

import com.moyo.Beans.TipEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.SessionFactory.SF.getSession;

public class TipDAO {
    public void save(TipEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.save(object);
            transaction.commit();
        }catch (Exception e){
            throw e;
        }
    }
    public void delete(TipEntity object){
        try{
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }catch (Exception e){
            throw e;
        }
    }
    public TipEntity findById(String id){
        try{
            TipEntity object=(TipEntity) getSession().get(TipEntity.class,id);
            return object;
        }catch (Exception e){
            throw e;
        }
    }
    public List findByProperty(String property, Object o){
        try{
            String queryString="from TipEntity as ude where ude."+property+"=?";
            Query queryObject=getSession().createQuery(queryString);
            queryObject.setParameter(0,o);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List findAll(){
        try{
            String queryString = "from TipEntity";
            Query queryObject=getSession().createQuery(queryString);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public TipEntity merge(TipEntity detachedInstance) {
        try {
            TipEntity result = (TipEntity) getSession().merge(detachedInstance);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(TipEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }
    public void attachClean(TipEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List findByBatchId(Object o){
        return findByProperty("batchId",o);
    }
    public List findByManagerId(Object o){
        return findByProperty("managerId",o);
    }
    public List findByTips(Object o){
        return findByProperty("tips",o);
    }
}
