package com.moyo.dao;

import com.moyo.beans.ManagerDetailEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.sessionfactory.SF.getSession;

public class ManagerDetailDAO {
    public void save(ManagerDetailEntity object){
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
    public void delete(ManagerDetailEntity object){
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
    public ManagerDetailEntity findById(long id){
        try{
            ManagerDetailEntity object=(ManagerDetailEntity) getSession().get(ManagerDetailEntity.class,id);
            return object;
        }catch (Exception e){
            throw e;
        }
    }
    public List findByProperty(String property, Object o){
        try{
            String queryString="from ManagerDetailEntity as ude where ude."+property+"=?";
            Query queryObject=getSession().createQuery(queryString);
            queryObject.setParameter(0,o);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public List findAll(){
        try{
            String queryString = "from ManagerDetailEntity";
            Query queryObject=getSession().createQuery(queryString);
            return queryObject.list();
        }catch (Exception e){
            throw e;
        }
    }
    public ManagerDetailEntity merge(ManagerDetailEntity detachedInstance) {
        try {
            Session session=getSession();
            Transaction transaction=session.beginTransaction();
            ManagerDetailEntity result = (ManagerDetailEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(ManagerDetailEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(ManagerDetailEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List findByBatchId(Object o){
        return findByProperty("batchId",o);
    }
    public List findByBirthYear(Object o){
        return findByProperty("birthYear",o);
    }
    public List findByEmail(Object o){
        return findByProperty("email",o);
    }
    public List findByGender(Object o){
        return findByProperty("gender",o);
    }
    public List findByMobile(Object o){
        return findByProperty("mobile",o);
    }
    public List findByName(Object o){
        return findByProperty("name",o);
    }
    public List findByNickname(Object o){
        return findByProperty("nickname",o);
    }
    public List findByPassword(Object o){
        return findByProperty("password",o);
    }
    public List findByUsername(Object o){
        return findByProperty("userName",o);
    }
}
