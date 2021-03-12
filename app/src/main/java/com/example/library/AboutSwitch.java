package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutSwitch extends AppCompatActivity {
    private Button mBack1;
    private TextView mTi_shi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_switch);
        initView();

        mBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zm=new Intent(AboutSwitch.this,BookExtratDetail.class);
                startActivity(zm);
            }
        });

}

    private void initView() {
        mBack1=(Button)findViewById(R.id.back1) ;
        mTi_shi=(TextView)findViewById(R.id.ti_shi);
    }
    }
