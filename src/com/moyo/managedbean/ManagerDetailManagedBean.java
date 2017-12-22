package com.moyo.managedbean;

import com.moyo.beans.ManagerDetailEntity;
import com.moyo.dao.ManagerDetailDAO;
import com.moyo.util.EncodeMD5;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@ManagedBean
@SessionScoped
public class ManagerDetailManagedBean {
    private long managerId;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private Date birthYear;
    private String nickname;
    private Long mobile;
    private String email;

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

    public Date getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Date birthYear) {
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

    /**
     * To set all the valuable null
     */
    private void setNullToAll(){
        managerId=0;
        birthYear=null;
        email=null;
        gender=null;
        mobile=null;
        name=null;
        nickname=null;
        password=null;
        username=null;
    }
    /**
     * To get HttpSession
    * */
    private HttpSession getHttpSession(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        return session;
    }
    /**
    * Manager Login Method
    * */
    public void managerLogin(){
        ManagerDetailDAO managerDetailDAO=new ManagerDetailDAO();
        ManagerDetailEntity managerDetailEntity= (ManagerDetailEntity) managerDetailDAO.findByUsername(username).get(0);
        try {
            String endPassword= EncodeMD5.encode(managerDetailEntity.getPassword());
            if(password.equals(endPassword)){
                HttpSession session=getHttpSession();
                session.setAttribute("managerId",managerDetailEntity.getManagerId());
                return;//if login success
            }else {
                return;//if login failed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Manager Logout method
     */
    public void managerLogout(){
        HttpSession session=getHttpSession();
        session.setAttribute("managerId",null);
        setNullToAll();
    }
}
