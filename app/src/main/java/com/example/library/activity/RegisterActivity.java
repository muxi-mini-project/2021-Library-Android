package com.example.library.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.Edits;
import android.os.Bundle;
import android.util.Log;
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
/**
 *注册页面！！！不是登录
 */


public class RegisterActivity extends AppCompatActivity {
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
        mRegister_username = (EditText)findViewById(R.id.register_username);
        mRegister_password = (EditText)findViewById(R.id.register_password);
        mPassword_again = (EditText)findViewById(R.id.password_again);
        mSureOfRegister = (Button)findViewById(R.id.sureofregister);
        //对控件初始化

        mSureOfRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mRegister_username.getText().toString();
                String password1 = mRegister_password.getText().toString();
                String password2 = mPassword_again.getText().toString();
                Log.d("RegisterActivity","用户名"+name+"第一次输入密码"+password1+"第二次输入密码"+password2);
                Register(name,password1,password2);
            }
        });
    }

    public void Register(String s1,String s2,String s3){
        if(s1.length()!=0&&s2.length()!=0&&s2.equals(s3)==true) {
            set_date(s1, s2);
        }
        else if(s2.length()==0){
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT);
        }
        else if(s2.equals(s3)==false){
            Toast.makeText(RegisterActivity.this,"两次密码输入不同",Toast.LENGTH_SHORT).show();
            Log.d("RegisterActivity","用户名"+s1+"第一次输入密码"+s2+"第二次输入密码"+s3);
        }else {
            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
        }

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
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
