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

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.library.AboutSwitch;
import com.example.library.Interface.BookExtractInterface;
import com.example.library.R;



import com.example.library.R;
import com.example.library.activity.GuideActivity;
import com.example.library.activity.LoginActivity;
import com.example.library.fragment.ChoseBookExtract;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.*;
import static com.example.library.fragment.ChoseBookExtract.mBook_extract_list;

public class BookExtratDetail extends AppCompatActivity implements View.OnClickListener {

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
    public static List<BookDigestData.DataDTO> mBook_extract_list = new ArrayList<>();
    Context context;
    BookDigestData.DataDTO mData =new BookDigestData.DataDTO(context);

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
        mAdapter = new BookExtractAdapter(BookExtratDetail.this, mBook_extract_list);
        initView();
        getRequest();
       getRequest1();
        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookExtratDetail.this.finish();
            }
        });
        mFinish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mData.getTitle()==null &&  mData.getSummary_information()==null && mData.getChapter()==null && mData.getThought()==null){
                    Toast.makeText(BookExtratDetail.this,"书摘不能为空", LENGTH_LONG).show();
                }
                else
               Toast.makeText(BookExtratDetail.this,"已添加", LENGTH_SHORT).show();
                Intent intent=new Intent(BookExtratDetail.this, ChoseBookExtract.class);
                startActivity(intent);
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

    private void getRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.71.184.107:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
        Call<BookDigestData.DataDTO>  bookDigestDataCall=mApi.getDigest(new BookDigestData());
        bookDigestDataCall.enqueue(new Callback<BookDigestData.DataDTO>() {
            @Override
            public void onResponse(Call<BookDigestData.DataDTO> call, Response<BookDigestData.DataDTO> response) {
                mFinish1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.getTitle() == null && mData.getSummary_information() == null && mData.getChapter() == null && mData.getThought() == null) {
                            Toast.makeText(BookExtratDetail.this, "书摘不能为空", LENGTH_LONG).show();
                        } else {
                            mAdapter = new BookExtractAdapter(BookExtratDetail.this, mBook_extract_list);
                            makeText(BookExtratDetail.this, "已添加", LENGTH_SHORT).show();
                            Intent intent = new Intent(BookExtratDetail.this, ChoseBookExtract.class);
                            //((Activity) mContext).finish();
                            Bundle bundle = new Bundle();
                            bundle.putString("Bookname", title.getText().toString());
                            bundle.putString("Context", summary_information.getText().toString());
                            intent.putExtras(bundle);
                            finish();
                            startActivity(intent);
                            mAdapter.addData(mBook_extract_list.size());
                           mData= response.body();
                        }
                    }
                });
            }
            @Override
            public void onFailure(Call<BookDigestData.DataDTO> call, Throwable t) {
             mFinish1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(BookExtratDetail.this,"添加失败", LENGTH_SHORT).show();
                 }
             });
            }
        });
    }

    private void getRequest1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.71.184.107:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
        Call<BookDigestData> bookDigestData = mApi.getPut(LoginActivity.token,mData.getBook_id());
        bookDigestData.enqueue(new Callback<BookDigestData>() {
            @Override
            public void onResponse(Call<BookDigestData> call, Response<BookDigestData> response) {
                mSwitch_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(BookExtratDetail.this,"公开成功", LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onFailure(Call<BookDigestData> call, Throwable t) {
                mSwitch_look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     Toast.makeText(BookExtratDetail.this,"公开失败", LENGTH_SHORT).show();
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
        chapter=(EditText)findViewById(R.id.chapter);
        summary_information=(EditText)findViewById(R.id.chapter_context);
        thought=(EditText)findViewById(R.id.idea);
        mFinish1=(Button)findViewById(R.id.finish1);
    }


    @Override
    public void onClick(View v) {

    }
}
