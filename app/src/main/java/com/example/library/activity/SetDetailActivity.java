package com.example.library.activity;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class SetDetailActivity extends AppCompatActivity {

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private Button mButton;
    private ImageButton mbutton1;
    private ImageButton mbutton2;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_detail);

        mButton = (Button)findViewById(R.id.set_back_button);
        mbutton1 = (ImageButton)findViewById(R.id.yonghushouce_button);
        mbutton2 = (ImageButton)findViewById(R.id.yinsizhengce_button);

        textView1 = (TextView)findViewById(R.id.set_back_text);
        textView2 = (TextView)findViewById(R.id.yonghushouce_text);
        textView3 = (TextView)findViewById(R.id.yinsizhengce_text);
        textView4 = (TextView)findViewById(R.id.tuichudenglu_text);


        linearLayout1 = (LinearLayout)findViewById(R.id.set_back_all);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(SetDetailActivity.this,"1",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        linearLayout2 = (LinearLayout)findViewById(R.id.zhanghuxinxi_all);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(SetDetailActivity.this,"2",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        linearLayout3 = (LinearLayout)findViewById(R.id.yonghushouce_all);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(SetDetailActivity.this,"3",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        linearLayout4 = (LinearLayout)findViewById(R.id.yinsizhengce_all);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(SetDetailActivity.this,AnswerActivity.class);
                intent.putExtra("X",index);
                startActivity(intent);*/
                Toast toast = Toast.makeText(SetDetailActivity.this,"4",Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        linearLayout5 = (LinearLayout)findViewById(R.id.tuichudenglu_all);
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(SetDetailActivity.this,"5",Toast.LENGTH_SHORT);
                toast.show();
            }
        });



    }
}