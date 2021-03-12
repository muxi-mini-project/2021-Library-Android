package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.activity.RegisterActivity;


public class Sure extends AppCompatActivity implements View.OnClickListener {
    private Button mSureofregister;
    private Button mBack;
    private EditText mResigter_username;
    private EditText mResigter_password;
    private EditText mPassword_again;
    private NewuserManager mNewuserManager;
    private SharedPreferences login_sp;
    //private CheckBox mRememberCheck;
    private String userNameValue,passwordValue;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_note);
        //对控件初始化
        initView();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(Sure.this, RegisterActivity.class);
                startActivity(c);
            }
        });
        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        //if(choseRemember){
            //mResigter_username.setText(name);
           // mResigter_password.setText(pwd);
            //mSureofregister.setChecked(true);
       // }
        if (mNewuserManager == null) {
            mNewuserManager = new NewuserManager(this);
            mNewuserManager.openDataBase();                              //建立本地数据库
        }
        View.OnClickListener m_register_Listener = new View.OnClickListener() {    //不同按钮按下的监听事件选择
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sureofregister:                       //确认按钮的监听事件
                        register_check();
                        break;
                    //case R.id.register_btn_cancel:                     //取消按钮的监听事件,由注册界面返回登录界面
                        //Intent intent_Register_to_Login = new Intent(Register.this,Login.class) ;    //切换User Activity至Login Activity
                        //startActivity(intent_Register_to_Login);
                }
            }
        };
    }

    private void register_check() {
        if (isUserNameAndPwdValid()) {
            String userName = mResigter_username.getText().toString().trim();
            String userPwd = mResigter_password.getText().toString().trim();
            //String userPwdCheck = mPwdCheck.getText().toString().trim();
            //检查用户是否存在
            int count=mNewuserManager.findUserByName(userName);
            //用户已经存在时返回，给出提示文字
            if(count>0){
                Toast.makeText(this, getString(R.string.name_already_exist),Toast.LENGTH_SHORT).show();
                return ;
            }

            if(userPwd.equals(userPwd)==false){     //两次密码输入不一样
                Toast.makeText(this, getString(R.string.different),Toast.LENGTH_SHORT).show();
                return ;
            } else {
               NewuserData mUser = new NewuserData(userName, userPwd);
                mNewuserManager.openDataBase();
                boolean flag = mNewuserManager.insertUserData(mUser); //新建用户信息
                if (flag == false) {
                    Toast.makeText(this, getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(Sure.this,RegisterActivity.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
        }

    }

    private boolean isUserNameAndPwdValid() {
        if (mResigter_username.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.resigter_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mResigter_password.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.resigiter_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onResume() {
        if (mNewuserManager == null) {
            mNewuserManager = new NewuserManager(this);
            mNewuserManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mNewuserManager != null) {
            mNewuserManager.closeDataBase();
            mNewuserManager = null;
        }
        super.onPause();
    }


    // private void register_check() {
    //}

    private void initView() {
        mBack = (Button) findViewById(R.id.back);
        mResigter_password=(EditText)findViewById(R.id.register_password);
        mResigter_username=(EditText) findViewById(R.id.register_username);
        mPassword_again=(EditText)findViewById(R.id.password_again);
        mSureofregister = (Button) findViewById(R.id.sureofregister);
        mSureofregister.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    // @Override
    //public void onClick(View v) {
       // switch (v.getId()) {
           // case R.id.sureofregister:
               // final String newusername = mResigter_username.getText().toString().trim();
               // final String newpassword = mResigter_password.getText().toString().trim();
               // Newuser newuser = new Newuser();
               // newuser.newusername = newusername;
               // newuser.newpassword = newpassword;
                //boolean newuserInfo = submit(newuser);
               // if (newuserInfo) {
               //  Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT);
              //  }else{
              //      Toast.makeText(this,"账户密码不能为空",Toast.LENGTH_SHORT);
             //   }
       // }

  //  }

   // private boolean submit(Newuser newuser) {
     //   if (TextUtils.isEmpty(newuser.newusername) || TextUtils.isEmpty(newuser.newpassword)) {
         //   Toast.makeText(this, "账户密码不能为空", Toast.LENGTH_SHORT).show();
        //    return false;
      //  } else {
       //     return true;
       // }
   // }
}
