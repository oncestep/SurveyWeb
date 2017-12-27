package com.moyo.dao;

import com.moyo.beans.UserDetailEntity;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static com.moyo.sessionfactory.SF.getSession;


public class UserDetailDAO {

    public void save(UserDetailEntity object) {
        try {
            //通过SessionFactory的getSession得到session
            Session session = getSession();

            //获得批次
            Transaction transaction = session.beginTransaction();

            //session执行持久化操作
            session.save(object);

            //关闭批次
            transaction.commit();

            //清除关闭session
            session.clear();
            session.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(UserDetailEntity object) {
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
<<<<<<< HEAD
    public UserDetailEntity findById(Long id){
        try{
            UserDetailEntity object=(UserDetailEntity) getSession().get(UserDetailEntity.class,id);
=======

    public UserDetailEntity findById(long id) {
        try {
            UserDetailEntity object = (UserDetailEntity) getSession().get(UserDetailEntity.class, id);
>>>>>>> backup
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

    public List findByProperty(String property, Object o) {
        try {
            String queryString = "from UserDetailEntity as ude where ude." + property + "=?";

            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, o.toString());

            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public List findByBirthYear(Object o) {
        return findByProperty("birthYear", o);
    }

    public List findByEmail(Object o) {
        return findByProperty("email", o);
    }

    public List findByGender(Object o) {
        return findByProperty("gender", o);
    }

    public List findByMobile(Object o) {
        return findByProperty("mobile", o);
    }

    public List findByName(Object o) {
        return findByProperty("name", o);
    }

    public List findByNickname(Object o) {
        return findByProperty("nickname", o);
    }

    public List findByPassword(Object o) {
        return findByProperty("password", o);
    }

    public List findByUsername(Object o) {
        return findByProperty("userName", o);
    }

    public List findAll() {
        try {
            String queryString = "from UserDetailEntity";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (Exception e) {
            throw e;
        }
    }

    public UserDetailEntity merge(UserDetailEntity detachedInstance) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();

            UserDetailEntity result = (UserDetailEntity) session.merge(detachedInstance);
            transaction.commit();
            session.clear();
            session.close();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachDirty(UserDetailEntity instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (Exception e) {
            throw e;
        }
    }

    public void attachClean(UserDetailEntity instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
