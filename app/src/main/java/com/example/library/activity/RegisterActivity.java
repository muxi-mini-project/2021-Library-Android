package com.example.library.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.Interface.RegisterService;
import com.example.library.NewuserData;
import com.example.library.NewUserManager;
import com.example.library.R;
import com.example.library.data.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mSureOfRegister;
    private Button mBack;
    private EditText mRegister_username;
    private EditText mRegister_password;
    private EditText mPassword_again;
    private NewUserManager mNewUserManager;
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
                Intent c=new Intent(RegisterActivity.this, LoginActivity.class);
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
        if (mNewUserManager == null) {
            mNewUserManager = new NewUserManager(this);
            mNewUserManager.openDataBase();                              //建立本地数据库
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
            String userName = mRegister_username.getText().toString().trim();
            String userPwd = mRegister_password.getText().toString().trim();
            //String userPwdCheck = mPwdCheck.getText().toString().trim();
            //检查用户是否存在
            int count= mNewUserManager.findUserByName(userName);
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
                mNewUserManager.openDataBase();
                boolean flag = mNewUserManager.insertUserData(mUser); //新建用户信息
                if (flag == false) {
                    Toast.makeText(this, getString(R.string.register_fail),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.register_success),Toast.LENGTH_SHORT).show();
                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this, LoginActivity.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
        }

    }

    private boolean isUserNameAndPwdValid() {
        if (mRegister_username.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.resigter_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mRegister_password.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.resigiter_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onResume() {
        if (mNewUserManager == null) {
            mNewUserManager = new NewUserManager(this);
            mNewUserManager.openDataBase();
        }
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        if (mNewUserManager != null) {
            mNewUserManager.closeDataBase();
            mNewUserManager = null;
        }
        super.onPause();
    }


    // private void register_check() {
    //}

    private void initView() {
        mBack = (Button) findViewById(R.id.back);
        mRegister_password =(EditText)findViewById(R.id.register_password);
        mRegister_username =(EditText) findViewById(R.id.register_username);
        mPassword_again=(EditText)findViewById(R.id.password_again);
        mSureOfRegister = (Button) findViewById(R.id.sureofregister);
        mSureOfRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_date(userNameValue,passwordValue);
            }
        });
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void set_date(String name,String password){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterService login = retrofit.create(RegisterService.class);

        /*接收返回的类*/

        Call<Users> userCall = login.getCall(new Users(name, password));
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful() == true) {
                    String user_name = response.body().getUser_name();
                    String user_motto = response.body().getMotto();
                    Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,GuideActivity.class);
                } else {
                    Toast.makeText(RegisterActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
