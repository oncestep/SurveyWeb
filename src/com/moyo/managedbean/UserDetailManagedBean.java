package com.moyo.managedbean;

import com.moyo.beans.SurveyEntity;
import com.moyo.beans.UserDetailEntity;
import com.moyo.dao.UserDetailDAO;
import com.moyo.util.EncodeMD5;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserDetailManagedBean {
    private long userId;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private java.util.Date birthYear;
    private String nickname;
    private Long mobile;
    private String email;
    //用户名提示
    private String usernameTip;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
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

    public String getUsernameTip() {
        return usernameTip;
    }

    public void setUsernameTip(String usernameTip) {
        this.usernameTip = usernameTip;
    }

    private UserDetailEntity getEntity() {
        java.sql.Date birthYearSQL = new java.sql.Date(birthYear.getTime());
        UserDetailEntity entity = new UserDetailEntity();
        entity.setUserName(username);
        entity.setPassword(password);
        entity.setName(name);
        entity.setGender(gender);
        entity.setBirthYear(birthYearSQL);
        entity.setNickname(nickname);
        entity.setMobile(mobile);
        entity.setEmail(email);

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

    /**
     * 用户注册
     *
     * @return
     */
    public boolean userRegister() {
        UserDetailDAO userDAO = new UserDetailDAO();

        List<UserDetailEntity> userList = userDAO.findByUsername(username);

        if (userList.isEmpty() == true) {
            try {
                password = EncodeMD5.encode(password);
                userDAO.save(getEntity());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 用户登录
     *
     * @return
     */
    public boolean userLogin() {
        UserDetailDAO userDAO = new UserDetailDAO();
        List<UserDetailEntity> userList = (List<UserDetailEntity>) userDAO.findByUsername(username);

        if (userList.isEmpty() == false) {
            try {
                UserDetailEntity user = userList.get(0);
                String encPassword = EncodeMD5.encode(password);
                if (encPassword.equals(user.getPassword())) {

                    /*  将用户信息存入UserManagerBean  */
                    userId = user.getUserId();
                    name = user.getName();
                    gender = user.getGender();
                    birthYear = user.getBirthYear();
                    nickname = user.getNickname();
                    mobile = user.getMobile();
                    email = user.getEmail();

                    /*  将userId存入session    */
                    FacesContext facesContext = FacesContext.getCurrentInstance();
                    ExternalContext extContext = facesContext.getExternalContext();
                    HttpSession session = (HttpSession) extContext.getSession(true);
                    session.setAttribute("userId", userId);

                    /*  调用SurveyManagerBean的showQuestion方法  */
                    SurveyManagedBean surBean = new SurveyManagedBean();
                    session.setAttribute("surveyManagedBean", surBean);
                    surBean.showAllSurvey(user.getUserId());

                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     * 更新用户信息
     *
     * @return
     */
    public String userUpdate() {
        //获取session
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        long userId = (long) session.getAttribute("userId");

        UserDetailDAO userDAO = new UserDetailDAO();
        UserDetailEntity user = userDAO.findById(userId);

        try {
            password = EncodeMD5.encode(password);
            UserDetailEntity userEntity = getEntity();
            userEntity.setUserId(userId);
            userDAO.merge(userEntity);
            return "/user/index.xhtml";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user/login.xhtml";
    }

    /**
     * 用户登出
     */
    public String userLogout() {
        //清除session
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(false);

        session.invalidate();
        return "/user/login.xhtml";
    }

}
