package com.moyo.AJAX;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;

@ManagedBean
@SessionScoped
public class RegisterManageBean1 {
    private String ID;
    private String username;
    private String name;
    private String password;
    private String cpassword;
    private int gender;
    private String nickname; 
    private Date birthday;
    private int phnumber;
    private String email;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(int phnumber) {
        this.phnumber = phnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public void validateUserName(FacesContext fc,UIComponent c,Object value){
        if(
                ((String)value).contains("!")||((String)value).contains("@")||
                ((String)value).contains("#")||((String)value).contains("&")||
                ((String)value).contains("$")||((String)value).contains("%")||
                ((String)value).contains("*"))
            throw new ValidatorException(new FacesMessage("Userame cannot contain special characters"));
          
}
   
}
