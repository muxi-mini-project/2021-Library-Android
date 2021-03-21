package com.example.library.data;

import android.service.autofill.UserData;

import java.util.List;

public class Users {
    /*
    *
    *
    *
    *
    * */
    private List<UserData> userData;
    private String massage;

    public List<UserData> getUserData(){
        return userData;
    }

    public void setUserData(List<UserData> userData) {
        this.userData = userData;
    }

    @Override
    public String toString(){
        return "UserDate {"+
                "date"  + userData +
                ", message" + massage + '\''
                + "}";
    }


    public static class UserDate{
        private String user_id;
        private String user_name;
        private String user_password;
        private String user_picture;
        private String motto;

        private String getUser_id(){
            return user_id;
        }
        private void setUser_id(String user_id){
            this.user_id = user_id;
        }

        private String getUser_name(){
            return user_name;
        }

        private void setUser_name(String user_name){
            this.user_name = user_name;
        }

        private String getUser_password(){
            return user_password;
        }
        private void setUser_password(){
            this.user_password = user_password;
        }

        private String getUser_picture(){
            return user_picture;
        }
        private void setUser_picture(String user_picture){
            this.user_picture = user_picture;
        }

        private String getMotto(){
            return motto;
        }
        private void setMotto(String motto){
            this.motto = motto;
        }

    }

}
