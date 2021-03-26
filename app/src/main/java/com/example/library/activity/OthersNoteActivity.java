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

import com.example.library.R;
import com.example.library.data.CommentData;
import com.example.library.data.CommentDetail;
import com.example.library.data.NotesLab;
import com.example.library.data.OthersDigestData;
import com.example.library.data.ReplyDetail;
import com.example.library.fragment.BookDetailsFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.List;

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
    private List<CommentDetail> mCommentList;
    private BottomSheetDialog dialog;
    private TextView bt_comment;
    private String testJson = "{\n" +
            "\t\"code\": 1000,\n" +
            "\t\"message\": \"查看评论成功\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"total\": 3,\n" +
            "\t\t\"list\": [{\n" +
            "\t\t\t\t\"id\": 42,\n" +
            "\t\t\t\t\"mCommentName\": \"程序猿\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"mComment\": \"时间是一切财富中最宝贵的财富。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"三分钟前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"mCommentName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\t\"commentId\": \"42\",\n" +
            "\t\t\t\t\t\"mComment\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"一个小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 41,\n" +
            "\t\t\t\t\"mCommentName\": \"设计狗\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"mComment\": \"这世界要是没有爱情，它在我们心中还会有什么意义！这就如一盏没有亮光的走马灯。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 1,\n" +
            "\t\t\t\t\"createDate\": \"一天前\",\n" +
            "\t\t\t\t\"replyList\": [{\n" +
            "\t\t\t\t\t\"mCommentName\": \"沐風\",\n" +
            "\t\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\t\"commentId\": \"41\",\n" +
            "\t\t\t\t\t\"content\": \"时间总是在不经意中擦肩而过,不留一点痕迹.\",\n" +
            "\t\t\t\t\t\"status\": \"01\",\n" +
            "\t\t\t\t\t\"createDate\": \"三小时前\"\n" +
            "\t\t\t\t}]\n" +
            "\t\t\t},\n" +
            "\t\t\t{\n" +
            "\t\t\t\t\"id\": 40,\n" +
            "\t\t\t\t\"mCommentName\": \"产品喵\",\n" +
            "\t\t\t\t\"userLogo\": \"http://ucardstorevideo.b0.upaiyun.com/userLogo/9fa13ec6-dddd-46cb-9df0-4bbb32d83fc1.png\",\n" +
            "\t\t\t\t\"mComment\": \"笨蛋自以为聪明，聪明人才知道自己是笨蛋。\",\n" +
            "\t\t\t\t\"imgId\": \"xcclsscrt0tev11ok364\",\n" +
            "\t\t\t\t\"replyTotal\": 0,\n" +
            "\t\t\t\t\"createDate\": \"三天前\",\n" +
            "\t\t\t\t\"replyList\": []\n" +
            "\t\t\t}\n" +
            "\t\t]\n" +
            "\t}\n" +
            "}";

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
        updateUI();

        //rv_comment.setLayoutManager(new LinearLayoutManager(OthersNoteActivity.this));
        //rv_comment.setAdapter(new CommentAdapter(notes, OthersNoteActivity.this));
    }

    /*实例化组件*/
    private void initView() {
        textView3 = (TextView) findViewById(R.id.textView3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        //rv_comment = (RecyclerView) findViewById(R.id.rv_comment);
        textView6 = (TextView) findViewById(R.id.textView6);
        expandableListView = findViewById(R.id.expandable_comment);
        expandableListView.setFocusable(false);
        bt_comment = (TextView) findViewById(R.id.detail_page_do_comment);
        bt_comment.setOnClickListener(this);
        mCommentList = generateTestData();
        initExpandableListView(mCommentList);

        Log.e(TAG,"data is>>>>>" + mCommentList.get(1).getCommentName() + "<<<<<<<<");
    }

    private void updateUI() {
        textView5.setText(mNote.getUser_id());
        textView3.setText(mNote.getDate());
        textView4.setText(mNote.getSummary_information());
    }
/*初始化评论和回复列表*/
    private void initExpandableListView(final List<CommentDetail> commentList){
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
                Log.e(TAG,"onGroupClick:当前的评论id >>>" + commentList.get(groupPosition).getId());
                //弹出回复框
                showReplyDialog(groupPosition);
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
/*生成评论数据*/
    private List<CommentDetail> generateTestData(){
        Gson gson = new Gson();
        commentData = gson.fromJson(testJson,CommentData.class);
        List<CommentDetail> commentList = commentData.getData().getList();
        return commentList;
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
                    CommentDetail comment = new CommentDetail("嗯哼",commentContent,"刚刚");//缺时间
                    adapter.addTheCommentData(comment);
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
    private void showReplyDialog(final int position){
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
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

/*
class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder> {
    private List<Notes> mNotes;
    Context mContext;

    public CommentAdapter(List<Notes> notes, Context context) {
        mNotes = notes;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new Holder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Notes notes = mNotes.get(position);
        holder.bind(notes);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView book_detail_note_pic;
        private TextView book_detail_name;
        private TextView book_detail_date;
        private TextView book_detail_note_content;
        private Notes mNotes;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_notes_list, parent, false));

            //暂时将原他人的书摘内容替换成评论内容，id对应书摘内容和评论内容——前提是一个人只有一条书摘和一条评论
                    book_detail_note_pic = (ImageView) itemView.findViewById(R.id.book_detail_note_pic);
            book_detail_name = (TextView) itemView.findViewById(R.id.book_detail_name);
            book_detail_date = (TextView) itemView.findViewById(R.id.book_detail_date);
            book_detail_note_content = (TextView) itemView.findViewById(R.id.book_detail_note_content);


        }

        private void bind(Notes notes) {
            mNotes = notes;
            book_detail_name.setText(mNotes.getNoteWriter());
            book_detail_note_content.setText(mNotes.getCMContent());
            book_detail_date.setText(mNotes.getNoteDate());
        }
    }
*/