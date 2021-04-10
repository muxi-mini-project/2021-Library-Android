package com.example.library.BookExtract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.library.AboutSwitch;
import com.example.library.Interface.BookExtractInterface;
import com.example.library.R;



import com.example.library.R;
import com.example.library.activity.GuideActivity;
import com.example.library.activity.LoginActivity;
import com.example.library.data.BookDigestData;
import com.example.library.data.GetDigest;
import com.example.library.fragment.ChoseBookExtract;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.*;

public class BookExtratDetail extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DQP";
    private Button mBack_book_extract;
    private Button mSwitch_detail;
    private Button mSwitch_look;
    public EditText title;
    public EditText chapter;
    public EditText summary_information;
    public EditText thought;
    private Button mFinish1;
    private Context mContext;
    private BookExtractAdapter mAdapter;
    public static List<GetDigest.DataDTO> mBook_extract_list = new ArrayList<>();
    Context context;
    BookDigestData.DataDTO mData =new BookDigestData.DataDTO();
    private String name;
    private String summary;
    private String thoughtdetail;
   private String chapterdetail;

    private DialogInterface.OnClickListener mListener=new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           // Toast.makeText(BookExtratDetail.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT);
            dialog.dismiss();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = getIntent().getStringExtra("书摘名称");
        summary=getIntent().getStringExtra("书摘内容");
        chapterdetail=getIntent().getStringExtra("书摘章节");
        thoughtdetail=getIntent().getStringExtra("书摘思考");

        setContentView(R.layout.book_extract_detail);
        mAdapter = new BookExtractAdapter(BookExtratDetail.this, mBook_extract_list);
        initView();
        inputData();
        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookExtratDetail.this.finish();
            }
        });

        mFinish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mData.getTitle()==""){
                    Log.e(TAG,"==========>>");
                }
                if (mData.getTitle() == "" && mData.getSummary_information() == "" && mData.getChapter() == "" && mData.getThought() == "") {
                    Toast.makeText(BookExtratDetail.this, "书摘不能为空", LENGTH_LONG).show();
                } else {
                    getRequest();
                }
            }

            private void getRequest() {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:10086")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
                Call<BookDigestData.DataDTO> bookDigestDataCall = mApi.getDigest(LoginActivity.token,mData);
                bookDigestDataCall.enqueue(new Callback<BookDigestData.DataDTO>() {
                    @Override
                    public void onResponse(Call<BookDigestData.DataDTO> call, Response<BookDigestData.DataDTO> response) {

                        Log.d(TAG, "创建书摘" + response.code());
                        makeText(BookExtratDetail.this, "已添加", LENGTH_SHORT).show();
                        mData = response.body();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<BookDigestData.DataDTO> call, Throwable t) {

                        Toast.makeText(BookExtratDetail.this, "添加失败", LENGTH_SHORT).show();
                    }

                });

                mSwitch_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BookExtratDetail.this);
                        builder.setTitle("提示");
                        builder.setMessage("按键打开时，此书摘在个人主页、书籍详情页以及摘录页面都可见。\n" +
                                "按键关闭时，此书摘在仅摘录页面可见。");
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });


                mSwitch_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getRequest1();
                    }

                    private void getRequest1() {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://39.102.42.156:10086")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);

                        Call<BookDigestData> bookDigestData = mApi.getPut(LoginActivity.token, mData.getBook_id());
                        bookDigestData.enqueue(new Callback<BookDigestData>() {
                            @Override
                            public void onResponse(Call<BookDigestData> call, Response<BookDigestData> response) {
                                if (response.code() == HttpURLConnection.HTTP_OK) {
                                    Toast.makeText(BookExtratDetail.this, "公开成功", LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(BookExtratDetail.this, "公开失败", LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BookDigestData> call, Throwable t) {
                                Toast.makeText(BookExtratDetail.this, "无网络链接", LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        mBack_book_extract=(Button)findViewById(R.id.back_book_extract);
        mSwitch_detail=(Button) findViewById(R.id.switch_detail);
        mSwitch_look=(Button)findViewById(R.id.switch_look);
        title=(EditText) findViewById(R.id.title);
        title.setText(name);
        chapter=(EditText)findViewById(R.id.chapter);
        chapter.setText(chapterdetail);
        summary_information=(EditText)findViewById(R.id.chapter_context);
         summary_information.setText(summary);
        thought=(EditText)findViewById(R.id.idea);
        thought.setText(thoughtdetail);
        mFinish1=(Button)findViewById(R.id.finish1);
    }

    private void inputData(){
        mData.setTitle(title.getText().toString());
        Log.e(TAG,">>>?>>.../...."+mData.getTitle());
        mData.setChapter(chapter.getText().toString());
        mData.setSummary_information(summary_information.getText().toString());
        mData.setThought(thought.getText().toString());
    }

    @Override
    public void onClick(View v) {

    }
}
