package com.example.library.BookExtract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.activity.GuideActivity;

public class BookExtratDetail extends AppCompatActivity implements View.OnClickListener {

    private Button mBack_book_extract;
    private Button mSwitch_detail;
    private Button mSwitch_look;
    private TextView mTitle;
    private EditText mChapter;
    private EditText mChapter_context;
    private EditText mIdea;
    private Button mFinish1;

    private DialogInterface.OnClickListener mListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           // Toast.makeText(BookExtratDetail.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT);
            dialog.dismiss();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.book_extract_detail);
        initView();
        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(BookExtratDetail.this, GuideActivity.class);
                //startActivity(intent);
                BookExtratDetail.this.finish();
            }
        });
        mFinish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookExtratDetail.this,"已完成",Toast.LENGTH_SHORT);
                //Intent intent=new Intent(BookExtratDetail.this, GuideActivity.class);
                //startActivity(intent);
                BookExtratDetail.this.finish();
            }
        });
        mSwitch_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(BookExtratDetail.this);
                builder.setTitle("提示");
                builder.setMessage("按键打开时，此书摘在个人主页、书籍详情页以及摘录页面都可见。\n" +
                        "按键关闭时，此书摘在仅摘录页面可见。");
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }

    private void initView() {
        mBack_book_extract=(Button)findViewById(R.id.back_book_extract);
        mSwitch_detail=(Button)findViewById(R.id.switch_detail);
        mSwitch_look=(Button)findViewById(R.id.switch_look);
        mTitle=(TextView)findViewById(R.id.title);
        mChapter=(EditText)findViewById(R.id.chapter);
        mChapter_context=(EditText)findViewById(R.id.chapter_context);
        mIdea=(EditText)findViewById(R.id.idea);
        mFinish1=(Button)findViewById(R.id.finish1);
    }


    @Override
    public void onClick(View v) {

    }
}
