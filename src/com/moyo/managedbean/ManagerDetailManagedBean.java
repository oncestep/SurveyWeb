package com.moyo.managedbean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.moyo.beans.ManagerDetailEntity;
import com.moyo.dao.ManagerDetailDAO;
import com.moyo.util.EncodeMD5;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private int getGenderCode() {
        if (wGender.equals("male")) {
            gender = 0;
        } else {
            gender = 1;
        }
        return gender;
    }

    private ManagerDetailEntity getEntity() {
        ManagerDetailEntity managerDetailEntity = new ManagerDetailEntity();
        java.sql.Date birthYearSQL = null;
        if (birthYear != null) {
            birthYearSQL = new java.sql.Date(birthYear.getTime());
        }
        try {
            String encPassword = EncodeMD5.encode(password);
            if (wGender != null) {
                managerDetailEntity.setGender(getGenderCode());
            }
            managerDetailEntity.setBirthYear(birthYearSQL);
            managerDetailEntity.setEmail(email);
            managerDetailEntity.setMobile(mobile);
            managerDetailEntity.setName(name);
            managerDetailEntity.setNickName(nickname);
            managerDetailEntity.setPassword(encPassword);
            managerDetailEntity.setUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return managerDetailEntity;
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
                if(session.getAttribute("managerId")!=null){
                    session.setAttribute("managerId",null);
                }
                session.setAttribute("managerId", managerDetailEntity.getManagerId());
                session.setAttribute("type",1);
                /*  手动初始化surveyManagedBean  */
                SurveyManagedBean surveyManagedBean = new SurveyManagedBean();
                session.setAttribute("surveyManagedBean", surveyManagedBean);
                return "true";//if login success
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    /**
     * Manager Logout method
     */
    public String managerLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(false);

        session.invalidate();
        return "managerLogin";
    }

    public String showPersonalDetail() {
        ManagerDetailDAO managerDetailDAO = new ManagerDetailDAO();
        HttpSession session = getHttpSession();
        long managerid = (long) session.getAttribute("managerId");
        ManagerDetailEntity managerEntity = managerDetailDAO.findById(managerid);
        this.password = managerEntity.getPassword();
        this.email = managerEntity.getEmail();
        this.name = managerEntity.getName();
        this.nickname = managerEntity.getNickName();
        this.mobile = managerEntity.getMobile();
        return "ModifyPersonalInformation?faces-redirect=true";
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

    public String managerRegister() {
        try {
            ManagerDetailDAO managerDetailDAO = new ManagerDetailDAO();
            ManagerDetailEntity manager = getEntity();
            if (manager != null) {
                managerDetailDAO.merge(manager);
            }
        } catch (Exception e) {
            throw e;
        }
        return "managerLogin";
    }

    public String managerUpdate() {
        try {
            ManagerDetailDAO managerDetailDAO = new ManagerDetailDAO();
            ManagerDetailEntity manager = getEntity();

            HttpSession session = getHttpSession();
            manager.setManagerId((long)session.getAttribute("managerId"));
            if (manager != null) {
                managerDetailDAO.merge(manager);
            }
        } catch (Exception e) {
            throw e;
        }
        return "ManagerIndex";
    }
    public void validateEmail(FacesContext fc,UIComponent c,Object value){
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher((CharSequence) value);
        boolean flag = matcher.matches();
//        String reg = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";

        if(!flag)
            throw new ValidatorException(new FacesMessage("Must enter the correct email format!"));
    }

}
