package com.example.library.data;

public class User {
    private String user_name;
    private String Motto;
    private String user_picture;
    private String user_password;

    public User(String name,String motto,String password,String picture){
        this.Motto = motto;
        this.user_name = name;
        this.user_password = password;
        this.user_picture = picture;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getMotto() {
        return Motto;
    }

    public void setMotto(String motto) {
        this.Motto = motto;
    }
    public String getPicture(){
        return user_picture;
    }
    public void setUser_picture(String picture){
        this.user_picture = picture;
    }
}
