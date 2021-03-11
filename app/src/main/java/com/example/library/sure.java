package com.example.library;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.activity.RegisterActivity;

public class sure extends AppCompatActivity implements View.OnClickListener {
    private Button mSureofregister;
    private Button mBack;
    private EditText mResigter_username;
    private EditText mResigter_password;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_note);
        //对控件初始化
        initView();
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(sure.this,"返回",Toast.LENGTH_SHORT);
                Intent b=new Intent(sure.this, RegisterActivity.class);
                startActivity(b);
            }
        });
    }

    private void initView() {
        mBack = (Button) findViewById(R.id.back);
        mResigter_password=(EditText)findViewById(R.id.register_password);
        mResigter_username=(EditText) findViewById(R.id.register_username);
        mSureofregister = (Button) findViewById(R.id.sureofregister);
        mSureofregister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sureofregister:
                final String newusername = mResigter_username.getText().toString().trim();
                final String newpassword = mResigter_password.getText().toString().trim();
                Newuser newuser = new Newuser();
                newuser.newusername = newusername;
                newuser.newpassword = newpassword;
                boolean newuserInfo = submit(newuser);
                if (newuserInfo) {
                 Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(this,"账户密码不能为空",Toast.LENGTH_SHORT);
                }
        }

    }

    private boolean submit(Newuser newuser) {
        if (TextUtils.isEmpty(newuser.newusername) || TextUtils.isEmpty(newuser.newpassword)) {
            Toast.makeText(this, "账户密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
