package com.moyo.ManagedBean;

import com.moyo.Beans.UserDetailEntity;
import com.moyo.DAO.UserDetailDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserDetailManagedBean {
    private Long userId;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private java.util.Date birthYear;
    private String nickname;
    private Integer mobile;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public java.util.Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(java.util.Date birthYear) {
        this.birthYear = birthYear;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private UserDetailEntity getEntity() {
        UserDetailEntity entity = new UserDetailEntity();
        entity.setUserId(userId);
        java.sql.Date sqlbirthyear = new java.sql.Date(birthYear.getTime());
        entity.setBirthYear(sqlbirthyear);
        entity.setEmail(email);
        entity.setGender(gender);
        entity.setMobile(mobile);
        entity.setName(name);
        entity.setNickname(nickname);
        entity.setUsername(username);
        entity.setPassword(password);
        return entity;
    }

    public void insert() {
        try {
            UserDetailDAO udd = new UserDetailDAO();
            udd.save(getEntity());
        } catch (Exception e) {
            throw e;
        }
    }

    public void update() {
        try {
            UserDetailDAO udd = new UserDetailDAO();
            udd.merge(getEntity());
        } catch (Exception e) {
            throw e;
        }
    }
}
