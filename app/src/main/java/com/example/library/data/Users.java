package com.example.library.data;

import android.service.autofill.UserData;

import java.util.List;

public class Users {
    /**
     * 用于注册和登录的users类
     */
    private String user_name;
    private String user_password;
    private String motto;
    private String token;
    private String user_picture;

    public Users(String name, String password,String motto){
        this.user_name = name;
        this.user_password =password;
        this.motto = motto;
    }

    public Users(String name,String password){
        this.user_name = name;
        this.user_password = password;
    }

    public Users(String token){
        this.token = token;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String name) {
        this.user_name = name;
    }

    public String getUser_password(){
        return this.user_password;
    }

    public void setUser_password(String password){
        this.user_password = password;
    }

    public String getMotto(){
        return motto;
    }

    public void setMotto(String motto){
        this.motto = motto;
    }

    public String getToken(){
        return token;
    }
    public void setToken(String token){
        this.token = token;
    }

    public String getPicture(){
        return user_picture;
    }

    public void setPicture(String picture){
        this.user_picture = picture;
    }
}
