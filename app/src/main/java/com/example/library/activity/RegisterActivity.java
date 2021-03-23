package com.example.library.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.Interface.LoginService;
import com.example.library.R;
import com.example.library.data.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUsername;
    private EditText mPassword;
    private Button mLoading;
    private Button mResign;
    private ProgressDialog mProgressDialog;
    private final String FUCK = "这个人很懒，什么都没留下";
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //对控件初始化
        initView();
        //创建一个动态进度条
        mProgressDialog = new ProgressDialog(this);
        mResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(RegisterActivity.this, Sure.class);
                //startActivity(intent);
            }
        });
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLoading = (Button) findViewById(R.id.loading);
        mResign = (Button) findViewById(R.id.resign);
        mLoading.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loading:
                //final String username = mUsername.getText().toString().trim();
                //final String password = mPassword.getText().toString().trim();

                /**
                 *
                 * qyh写的登录接口部分
                 */
                String user = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                GET_REQUEST(user, password, FUCK);
            case R.id.resign:
                //Intent newIntent = new Intent(RegisterActivity.this,sure.class);
        }
    }

    public void GET_REQUEST(String name, String password, String motto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService login = retrofit.create(LoginService.class);

        /*接收返回的类*/

        Call<Users> userCall = login.getCall(new Users(name, password, motto));
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful() == true) {
                    token = response.body().getToken();
                    String user_name = response.body().getUser_name();
                    String user_motto = response.body().getMotto();
                    Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,GuideActivity.class);
                    intent.putExtra("GOTTOKEN",token);
                    intent.putExtra("GOTUSER_Name",user_name);
                    intent.putExtra("GOTUser_motto",user_motto);
                    startActivity(intent);
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







                /**
                 * dqp写的登录接口部分
                 */

                /*User user = new User();
                //user.username = username;
                //user.password = password;
                //判断信息是否为null
                boolean userInfo = submit(user);
                if (userInfo) {
                    //显示精度条
                    mProgressDialog.show();
                    //开子线程做耗时操做
                    new Thread() {
                        @Override
                        public void run() {
                            //模拟耗时操做
                            SystemClock.sleep(1500);
                            //关闭进度条
                            mProgressDialog.dismiss();
                            //对用户信息进行判断
                            if ("ccnu".equals(user.username) && "zmzka".equals(user.password)) {
                                //登录成功的逻辑，弹一个土司
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this,GuideActivity.class));
                                    }
                                });
                            } else {
                                //登录失败的逻辑，弹一个土司
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "账号或密码有误，请重新填写", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        };
                    }.start();
                } else {
                    Toast.makeText(RegisterActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }*/

        //对用户的输入进行非空判断
    /*private boolean submit(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            Toast.makeText(RegisterActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }*/


