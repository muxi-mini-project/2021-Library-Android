package com.example.library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.activity.GuideActivity;

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
     private AddDialog AddDialog;
     private EditText edit;
    private DialogInterface.OnClickListener mListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(edit.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT);
            dialog.dismiss();
           // Intent intent=new Intent(,edit.class);
          //  startActivity(intent);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit2);
        initView();
        LayoutInflater factory= LayoutInflater.from(edit.this);
        final View view=factory.inflate(R.layout.edit_add2,null);
        final EditText edit=(EditText)view.findViewById(R.id.add_text);

        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.library.edit.this.finish();
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.library.edit.this.finish();
            }
        });

        mAdd_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(edit.this);
                builder.setTitle("添加书摘种类");
                builder.setView(view);
                builder.setPositiveButton("确定",mListener);
                builder.setNegativeButton("取消",mListener);
               AlertDialog dialog=builder.create();
               dialog.show();

            }
        });

    }

    void initView() {
        mAdd=(TextView)findViewById(R.id.add);
        mAdd_look=(Button)findViewById(R.id.add_look);
        mBack_book_extract=(Button)findViewById(R.id.back_book_extract);
        mFinish=(Button)findViewById(R.id.finish);
       /* mAdd_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  editkindadd();
            }
        });*/

        //书摘种类的RecyclerView
        mBook_kind_recyclerview=(RecyclerView)findViewById(R.id.book_kind_recyclerview);
        List<String> list=new ArrayList<>();
        list.add("小说");
        list.add("历史");
        list.add("文学");
        list.add("诗歌");
        list.add("科幻");
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);
        mBook_kind_recyclerview.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mEditAdpapter=new EditAdpapter(this,list);
        mBook_kind_recyclerview.setAdapter(mEditAdpapter);

    }
    //为按钮绑定监听事件
    private void editkindadd(){
        //没有实例化一个对话框
        AlertDialog builder=new AlertDialog.Builder(AddDialog.getContext())
                .setView(new EditText(context))
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();

        builder.show();
    }


    @Override
    public void onClick(View v) {

    }
}
