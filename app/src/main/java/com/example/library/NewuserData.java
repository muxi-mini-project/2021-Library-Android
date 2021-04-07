package com.example.library;

public class NewuserData {
    public String newusername;
    public String newpassword;
    private int userId;                       //用户ID号
    public int pwdresetFlag=0;

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getNewusername() {
        return newusername;
    }

    public void setNewusername(String newusername) {
        this.newusername = newusername;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //public int getPwdresetFlag() {
     //   return pwdresetFlag;
    //}

    //public void setPwdresetFlag(int pwdresetFlag) {
       // this.pwdresetFlag = pwdresetFlag;
    //}
    public NewuserData(String newusername,String newpassword){
        super();
        this.newusername=newusername;
        this.newpassword=newpassword;

    }

}
