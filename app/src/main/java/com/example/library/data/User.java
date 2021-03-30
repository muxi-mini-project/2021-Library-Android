package com.example.library.data;

public class User {
    private String user_name;
    private String Motto;
    private String user_picture;

    public User(String name,String motto,String picture){
        this.Motto = motto;
        this.user_name = name;
        this.user_picture = picture;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        this.user_name = name;
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
