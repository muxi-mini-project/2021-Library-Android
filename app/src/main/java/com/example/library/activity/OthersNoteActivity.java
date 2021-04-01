package com.example.library.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.BookService;
import com.example.library.R;
import com.example.library.data.BookData;
import com.example.library.data.CommentData;
import com.example.library.data.CommentDetail;
import com.example.library.data.CommentPut;
import com.example.library.data.NotesLab;
import com.example.library.data.OthersDigestData;
import com.example.library.data.ReplyDetail;
import com.example.library.fragment.BookDetailsFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OthersNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String EXTRA_NOTE_ID = "com.example.Library.note_id";
    private static final String TAG = "com.example.library";
    private TextView textView3;
    private TextView textView5;
    private TextView textView4;
    private ImageView imageView3;
    private RecyclerView rv_comment;
    private TextView textView6;
    private OthersDigestData mNote;
    private ExpandableListView expandableListView;
    private CommentExpandAdapter adapter;
    private CommentDetail mComment;
    private CommentData commentData;
    private List<CommentData> mCommentList;
    private BottomSheetDialog dialog;
    private TextView bt_comment;

    //NotesLab notesLab = NotesLab.get(this);
    //private List<Notes> notes = notesLab.getNotes();

/*intent*/
    public static Intent newIntent(Context packageContext, int position) {
        Intent intent = new Intent(packageContext, OthersNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, position);
        return intent;
    }
/*onCreate*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note_content);
        int position = (int) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        mNote = BookDetailsFragment.mDataList.get(position);

        //getRequest();
        initView();
        getRequest();
        updateUI();

        //rv_comment.setLayoutManager(new LinearLayoutManager(OthersNoteActivity.this));
        //rv_comment.setAdapter(new CommentAdapter(notes, OthersNoteActivity.this));
    }

    public void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService mApi = retrofit.create(BookService.class);
        Call<List<CommentData>> commentDataCall = mApi.getCommentCall(LoginActivity.token,"1","2");
        Log.d(TAG,"the token is+++"+LoginActivity.token);

        commentDataCall.enqueue(new Callback<List<CommentData>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<CommentData>> call, Response<List<CommentData>> response) {
                Log.d(TAG,"评论的onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"评论的Json>>>>>" + response.body().toString());
                    mCommentList = response.body();
                    Log.d(TAG,"评论的data--------------" + mCommentList.toString());
                    //pics = getPicData(data);
                    initExpandableListView(mCommentList);
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<List<CommentData>> call, Throwable t)
            {
                Log.d(TAG,"error ---");
            }
        });
    }

    public void putComments(String book_id,String digest_id,String content){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService mApi = retrofit.create(BookService.class);
        Call<CommentPut> commentPut = mApi.putComment(LoginActivity.token,book_id,digest_id,new CommentPut(content));

        commentPut.enqueue(new Callback<CommentPut>() {
            @Override
            public void onResponse(Call<CommentPut> call, Response<CommentPut> response) {
                Log.d(TAG,"发出评论的onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"发出评论的Json>>>>>" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<CommentPut> call, Throwable t)
            {
                Log.d(TAG,"error ---");
            }
        });
    }


    /*实例化组件*/
    private void initView() {
        textView3 = (TextView) findViewById(R.id.textView3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        textView6 = (TextView) findViewById(R.id.textView6);
        expandableListView = findViewById(R.id.expandable_comment);
        expandableListView.setFocusable(true);
        expandableListView.setFocusableInTouchMode(true);
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment);
        bt_comment.setOnClickListener(this);

    }

    private void updateUI() {
        textView5.setText(mNote.getUser_id());
        textView3.setText(mNote.getDate());
        textView4.setText(mNote.getSummary_information());
    }
/*初始化评论和回复列表*/
    private void initExpandableListView(final List<CommentData> commentList){
        //取消ExpandableListView默认情况下自带分组icon（）
        expandableListView.setGroupIndicator(null);
        //适配器
        adapter = new CommentExpandAdapter(this, commentList);
        expandableListView.setAdapter(adapter);
        //默认展开所有的分组
        for (int i = 0 ; i < commentList.size() ; i++){
            expandableListView.expandGroup(i);
        }
    /*点击每条评论*/
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //点击后，该评论 “被expand”
                boolean isExpanded = expandableListView.isGroupExpanded(groupPosition);
                Log.e(TAG,"onGroupClick:当前的评论id >>>" + commentList.get(groupPosition).getReview_id());
                //弹出回复框
                //showReplyDialog(groupPosition);
                return true;
            }
        });
    /*点击每条回复*/
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(OthersNoteActivity.this,"点击了回复",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    /*展开*/
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                //toast("展开第" + groupPosition + "个分组")
            }
        });

    }

    @Override//???
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        if (view.getId() == R.id.detail_page_do_comment){
            showCommentDialog();
        }
    }

/*弹出评论框*/
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);//editText
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);//button

        dialog.setContentView(commentView);
        //干啥？？解决bsd显示不全的情况？？
        View parent = (View) commentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0,0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());

        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(commentContent)){
                    dialog.dismiss();//退出消失
                    //评论内容和对应评论的位置告知给适配器
                    CommentData comment = new CommentData("嗯哼",commentContent);//缺时间
                    adapter.addTheCommentData(comment);
                    //请求网络
                    putComments("1","2",comment.getContent());
                    //toast
                    Toast.makeText(OthersNoteActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OthersNoteActivity.this,"评论内容不能为空",Toast.LENGTH_SHORT);
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        //根据输入内容改变发布按钮的颜色
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.show();
    }
/*弹出回复框*/
    /*private void showReplyDialog(final int position){
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.comment_dialog,null);
        final EditText commentText = (EditText) commentView.findViewById(R.id.dialog_comment_et);//editText
        final Button bt_comment = (Button) commentView.findViewById(R.id.dialog_comment_bt);//button

        commentText.setHint("回复" + mCommentList.get(position).getCommentName() + " 的评论：");
        dialog.setContentView(commentView);
        bt_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String replyContent = commentText.getText().toString().trim();
                if (!TextUtils.isEmpty(replyContent)){
                    dialog.dismiss();//退出消失
                    //回复内容和对应评论的位置告知给适配器
                    ReplyDetail replyDetail = new ReplyDetail("拉拉",replyContent);
                    adapter.addTheReplyData(replyDetail,position);
                    //toast
                    Toast.makeText(OthersNoteActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OthersNoteActivity.this,"回复内容不能为空",Toast.LENGTH_SHORT);
                }
            }
        });
        commentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s) && s.length()>2){
                    bt_comment.setBackgroundColor(Color.parseColor("#FFB568"));
                }else {
                    bt_comment.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dialog.show();
    }*/


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
