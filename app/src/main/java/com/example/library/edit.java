package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class edit extends AppCompatActivity implements View.OnClickListener {
     private Button mBack_book_extract;
     private Button mFinish;
     private TextView mAdd;
     private Button mAdd_look;
     private RecyclerView mBook_kind_recyclerview;
     ArrayList<String>data=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit2);
        initView();

    }

    void initView() {
        mAdd=(TextView)findViewById(R.id.add);
        mAdd_look=(Button)findViewById(R.id.add_look);
        mBack_book_extract=(Button)findViewById(R.id.back_book_extract);
        //mBook_kind_recyclerview=(RecyclerView)findViewById(R.id.book_kind_recyclerview);
        mFinish=(Button)findViewById(R.id.finish);
        mAdd_look.setOnClickListener(this);
        mFinish.setOnClickListener(this);
        mBack_book_extract.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void back_extract(View view){

    }

    public void finish(View view) {
        Toast.makeText(this,"已保存您的修改",Toast.LENGTH_SHORT);
    }

    public void add(View view) {

    }
}
