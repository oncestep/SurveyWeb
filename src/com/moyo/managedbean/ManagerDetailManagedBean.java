package com.moyo.managedbean;

import com.moyo.beans.ManagerDetailEntity;
import com.moyo.dao.ManagerDetailDAO;
import com.moyo.util.EncodeMD5;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@ManagedBean
@SessionScoped
public class ManagerDetailManagedBean {
    private long managerId;
    private String username;
    private String password;
    private String cpassword;
    private String name;
    private String wGender;
    private Integer gender;
    private java.util.Date birthYear;
    private String nickname;
    private Long mobile;
    private String email;

    public String getwGender() {
        return wGender;
    }

    public void setwGender(String wGender) {
        this.wGender = wGender;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
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

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int getGenderCode(){
        if(wGender.equals("male")){
            gender=0;
        }else {
            gender=1;
        }
        return gender;
    }
    private ManagerDetailEntity getEntity() {
        ManagerDetailEntity managerDetailEntity = new ManagerDetailEntity();
        java.sql.Date birthYearSQL = new java.sql.Date(birthYear.getTime());
        try {
            String encPassword = EncodeMD5.encode(password);
            managerDetailEntity.setGender(getGenderCode());
            managerDetailEntity.setBirthYear(birthYearSQL);
            managerDetailEntity.setEmail(email);
            managerDetailEntity.setMobile(mobile);
            managerDetailEntity.setName(name);
            managerDetailEntity.setNickName(nickname);
            managerDetailEntity.setPassword(encPassword);
            managerDetailEntity.setUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerDetailEntity;
    }

    /**
     * To set all the valuable null
     */
    private void setNullToAll() {
        managerId = 0;
        birthYear = null;
        email = null;
        gender = null;
        mobile = null;
        name = null;
        nickname = null;
        password = null;
        username = null;
    }

    /**
     * To get HttpSession
     */
    private HttpSession getHttpSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        return session;
    }

    /**
     * Manager Login Method
     */
    public String managerLogin() {
        ManagerDetailDAO managerDetailDAO = new ManagerDetailDAO();
        ManagerDetailEntity managerDetailEntity = (ManagerDetailEntity) managerDetailDAO.findByUsername(username).get(0);
        try {
            String endPassword = EncodeMD5.encode(password);
            if (managerDetailEntity.getPassword().equals(endPassword)) {
                HttpSession session = getHttpSession();
                session.setAttribute("managerId", managerDetailEntity.getManagerId());
                return "index";//if login success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "managerRegister";
    }

    /**
     * Manager Logout method
     */
    public void managerLogout() {
        HttpSession session = getHttpSession();
        session.setAttribute("managerId", null);
        setNullToAll();
    }

    public void validateUserName(FacesContext fc, UIComponent c, Object value) {
        if (
                ((String) value).contains("!") || ((String) value).contains("@") ||
                        ((String) value).contains("#") || ((String) value).contains("&") ||
                        ((String) value).contains("$") || ((String) value).contains("%") ||
                        ((String) value).contains("*"))
            throw new ValidatorException(new FacesMessage("Username cannot contain special characters"));
    }

    public void validatePassword(FacesContext fc, UIComponent c, Object value) {
        if (c.getId().equals("magpwdR")) {
            password = (String) value;
        }

        if (!(password.equals(value))) {
            throw new ValidatorException(new FacesMessage("Not same as the password"));
        }
    }

    public void managerRegister() {
        try {
            ManagerDetailDAO managerDetailDAO = new ManagerDetailDAO();
            managerDetailDAO.save(getEntity());
        } catch (Exception e) {
            throw e;
        }
    }

}
