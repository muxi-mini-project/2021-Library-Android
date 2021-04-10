package com.example.library.BookExtract;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.library.Interface.BookExtractInterface;
import com.example.library.R;


import com.example.library.activity.LoginActivity;
import com.example.library.data.BookDigestData;
import com.example.library.data.GetDigest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.*;

public class BookExtratDetail extends Fragment implements View.OnClickListener {
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
    BookDigestData.DataDTO mData = new BookDigestData.DataDTO();
    private String name;
    private String summary;


    private DialogInterface.OnClickListener mListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            // Toast.makeText(BookExtratDetail.this,"Button"+which+"was clicked",Toast.LENGTH_SHORT);
            dialog.dismiss();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_extract_detail, container, false);
        Bundle bundle = getArguments();

        name = bundle.getString("get_DigestTitle");

        mBack_book_extract = (Button) v.findViewById(R.id.back_book_extract);
        mSwitch_detail = (Button) v.findViewById(R.id.switch_detail);
        mSwitch_look = (Button) v.findViewById(R.id.switch_look);
        title = (EditText) v.findViewById(R.id.title);
        title.setText(name);
        chapter = (EditText) v.findViewById(R.id.chapter);
        summary_information = (EditText) v.findViewById(R.id.chapter_context);
        // summary_information.setText(summary);
        thought = (EditText) v.findViewById(R.id.idea);
        mFinish1 = (Button) v.findViewById(R.id.finish1);

        mAdapter = new BookExtractAdapter(getContext(), mBook_extract_list);
        inputData();
        mBack_book_extract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        mFinish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.getTitle() == "") {
                    Log.e(TAG, "==========>>");
                }
                if (mData.getTitle() == "" && mData.getSummary_information() == "" && mData.getChapter() == "" && mData.getThought() == "") {
                    Toast.makeText(getActivity(), "书摘不能为空", LENGTH_LONG).show();
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
                Call<BookDigestData.DataDTO> bookDigestDataCall = mApi.getDigest(LoginActivity.token, mData);
                bookDigestDataCall.enqueue(new Callback<BookDigestData.DataDTO>() {
                    @Override
                    public void onResponse(Call<BookDigestData.DataDTO> call, Response<BookDigestData.DataDTO> response) {

                        Log.d(TAG, "创建书摘" + response.code());

                        //mAdapter = new BookExtractAdapter(BookExtratDetail.this, mBook_extract_list);
                        makeText(getActivity(), "已添加", LENGTH_SHORT).show();
                       /* Intent intent = new Intent(BookExtratDetail.this, ChoseBookExtract.class);
                        startActivity(intent);*/
                        // mAdapter.addData(mBook_extract_list.size() + 1);
                        mData = response.body();
                    }

                    @Override
                    public void onFailure(Call<BookDigestData.DataDTO> call, Throwable t) {

                        Toast.makeText(getActivity(), "添加失败", LENGTH_SHORT).show();
                    }

                });

                mSwitch_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
                                Toast.makeText(getActivity(), "公开成功", LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<BookDigestData> call, Throwable t) {
                                Toast.makeText(getActivity(), "公开失败", LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        return v;
    }

    public static BookExtratDetail newInstance( String digest_title) {
        Bundle arg = new Bundle();
        arg.putString("get_DigestTitle", digest_title);
        BookExtratDetail bookExtratDetail = new BookExtratDetail();
        bookExtratDetail.setArguments(arg);
        return bookExtratDetail;
    }


    private void inputData() {
        mData.setTitle(title.getText().toString());
        Log.e(TAG, ">>>?>>.../...." + mData.getTitle());
        mData.setChapter(chapter.getText().toString());
        mData.setSummary_information(summary_information.getText().toString());
        mData.setThought(thought.getText().toString());
    }

    @Override
    public void onClick(View v) {

    }
}
