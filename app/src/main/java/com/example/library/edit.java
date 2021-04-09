package com.example.library;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.library.Interface.BookExtractInterface;
import com.example.library.fragment.ChoseBookExtract;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.activity.GuideActivity;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class edit extends AppCompatActivity implements View.OnClickListener {
     private Button mBack_book_extract;
    private static final String TAG = "SZZL";
     private Button mFinish;
     private TextView mAdd;
     private Button mAdd_look;
     private RecyclerView mBook_kind_recyclerview;
     private EditAdpapter mEditAdpapter;
     private Context context;
     private AddDialog AddDialog;
     private EditText edit;
     private TextView mBook_name;
     private EditText mAdd_text;
    public List<String> kindlist=new ArrayList<>();
    private String message;

    private DialogInterface.OnClickListener mListener= (dialog, which) -> {
        Toast.makeText(edit.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT);
        dialog.dismiss();
       // Intent intent=new Intent(,edit.class);
      //  startActivity(intent);
    };

    private DialogInterface.OnClickListener  mListener1=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(edit.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT).show();
            addData(kindlist.size()+1);
            dialog.dismiss();
        }
    };
    //添加数据
    public void addData(int position){
        message = mAdd_text.getText().toString();
        //通知列表添加一条
        kindlist.add(message);
        if (kindlist.size() == 6){
            Log.e(TAG,"是否添加了"+kindlist.get(5));
        }else{
            Log.e(TAG,"!!!!!!!11");
        }
        // mBook_name.setText(message);
        //添加动画
        mEditAdpapter.notifyItemChanged(position);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit2);
        initView();
        LayoutInflater factory= LayoutInflater.from(edit.this);
        final View view=factory.inflate(R.layout.edit_add2,null);
        mAdd_text=(EditText)view.findViewById(R.id.add_text);

        final EditText edit=(EditText)view.findViewById(R.id.add_text);
       // getRequest();

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
                builder.setPositiveButton("确定",mListener1);
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
        mBook_name=(TextView)findViewById(R.id.book_name);
       /* mAdd_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  editkindadd();
            }
        });*/
        //书摘种类的RecyclerView
        mBook_kind_recyclerview=(RecyclerView)findViewById(R.id.book_kind_recyclerview);
        kindlist.add("小说");
        kindlist.add("历史");
        kindlist.add("文学");
        kindlist.add("诗歌");
        kindlist.add("科幻");
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);
        mBook_kind_recyclerview.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mEditAdpapter=new EditAdpapter(this,kindlist);
        mBook_kind_recyclerview.setAdapter(mEditAdpapter);
        mBook_kind_recyclerview.setItemAnimator(new DefaultItemAnimator());

    }

  /*  private void getRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.71.184.107:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
        Call<edit_item_java> edit_item_javaCall = mApi.getCall2();
        edit_item_javaCall.enqueue(new Callback<edit_item_java>() {
            @Override
            public void onResponse(Call<edit_item_java> call, Response<edit_item_java> response) {
                Log.d(TAG, "------------->>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Log.d(TAG, "+++=========>" + response.body().toString());
                    kindlist=response.body().getKindlist();
                }

            }

            @Override
            public void onFailure(Call<edit_item_java> call, Throwable t) {
                System.out.println("连接失败");
            }
        });
    }*/




    //为按钮绑定监听事件
    private void editkindadd(){
        //没有实例化一个对话框
        AlertDialog builder=new AlertDialog.Builder(AddDialog.getContext())
                .setTitle("请输入书摘种类")
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
