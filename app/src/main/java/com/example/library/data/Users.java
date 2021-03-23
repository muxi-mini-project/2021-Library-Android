package com.example.library.data;

import android.service.autofill.UserData;

import java.util.List;

public class Users {
    private String user_name;
    private String user_password;
    private String motto;
    private String token;

    public Users(String name, String password,String motto){
        this.user_name = name;
        this.user_password =password;
        this.motto = motto;
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
}
