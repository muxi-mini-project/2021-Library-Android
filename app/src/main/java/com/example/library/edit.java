package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.fragment.ChoseBookExtract;

import java.util.ArrayList;
import java.util.List;

public class edit extends AppCompatActivity implements View.OnClickListener {
     private Button mBack_book_extract;
     private Button mFinish;
     private TextView mAdd;
     private Button mAdd_look;
     private RecyclerView mBook_kind_recyclerview;
     private EditAdpapter mEditAdpapter;
     private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit2);
        initView();

        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent se=new Intent(edit.this, ChoseBookExtract.class);
                startActivity(se);
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sf=new Intent(edit.this, ChoseBookExtract.class);
                startActivity(sf);
            }
        });
    }

    void initView() {
        mAdd=(TextView)findViewById(R.id.add);
        mAdd_look=(Button)findViewById(R.id.add_look);
        mBack_book_extract=(Button)findViewById(R.id.back_book_extract);
        mFinish=(Button)findViewById(R.id.finish);
        mAdd_look.setOnClickListener(this);

        //
        mBook_kind_recyclerview=(RecyclerView)findViewById(R.id.book_kind_recyclerview);
        List<String> list=new ArrayList<>();
        list.add("小说");
        list.add("历史");
        list.add("文学");
        list.add("诗歌");
        list.add("科幻");
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(context);
        mBook_kind_recyclerview.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mEditAdpapter=new EditAdpapter(context,list);
        mBook_kind_recyclerview.setAdapter(mEditAdpapter);

    }

    @Override
    public void onClick(View v) {

    }
}
