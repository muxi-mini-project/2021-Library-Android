package com.example.library;

public class User {
    public String mUserName ;
    public String mMotto;

    public void User(String name,String motto){
        this.mMotto = motto;
        this.mUserName = name;
    }

    public String getName() {
        return mUserName;
    }

    public void setName(String name) {
        mUserName = name;
    }

    public String getMotto() {
        return mMotto;
    }

    public void setMotto(String motto) {
        mMotto = motto;
    }
}
