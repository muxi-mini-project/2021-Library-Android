package com.example.library;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sure extends AppCompatActivity implements View.OnClickListener {
    private Button mSureofregister;
    private Button mBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_note);
        //对控件初始化
        initView();
    }

    private void initView() {
        mBack=(Button) findViewById(R.id.back);
        mSureofregister = (Button) findViewById(R.id.sureofregister);
        mSureofregister.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT);
    }
}
