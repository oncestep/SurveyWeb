package com.moyo.managedbean;

import com.moyo.beans.UserDetailEntity;
import com.moyo.dao.UserDetailDAO;
import com.moyo.util.EncodeMD5;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserDetailManagedBean {
    private String id;
    List<UserDetailEntity> list=new ArrayList<>();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public List<UserDetailEntity> getList() {
        return list;
    }

    public void setList(List<UserDetailEntity> list) {
        this.list = list;
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

    public void userLogin(){
        UserDetailDAO userDAO = new UserDetailDAO();
        UserDetailEntity user = (UserDetailEntity) userDAO.findByUsername(username).get(0);
        try {
            String encPassword = EncodeMD5.encode(password);
            if(encPassword.equals(user.getPassword())){
                FacesContext context=FacesContext.getCurrentInstance();
                HttpSession session= (HttpSession) context.getExternalContext().getSession(true);
                session.setAttribute("userID",user.getUserId());
                return;
            }else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userLogout() {
        //清除session
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        session.setAttribute("userId", null);

        //初始化Bean
        username = null;
        password = null;
        name = null;
        gender = null;
        birthYear = null;
        nickname = null;
        mobile = null;
        email = null;
    }

    public void insert() {
        try {
            UserDetailDAO userDetailDAO = new UserDetailDAO();
            userDetailDAO.save(getEntity());
        } catch (Exception e) {
            throw e;
        }
    }

    public void update() {
        try {
            UserDetailDAO userDetailDAO = new UserDetailDAO();
            userDetailDAO.merge(getEntity());
        } catch (Exception e) {
            throw e;
        }
    }
    public void dopro(FacesContext facesContex, UIComponent uiComponent,Object o){
        UserDetailEntity user;
        UserDetailDAO userDetailDAO=new UserDetailDAO();
        user=userDetailDAO.findById(id);
        list.set(0,user);
    }


}
