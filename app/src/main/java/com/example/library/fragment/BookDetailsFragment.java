package com.example.library.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.library.Interface.BookService;
import com.example.library.RoundImageView;
import com.example.library.activity.OthersNoteActivity;
import com.example.library.R;
import com.example.library.data.BookData;
import com.example.library.data.OthersDigestData;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetailsFragment extends Fragment {
    private static final String ARG_BOOK_ID = "book_id";
    private static final String TAG = "BookDetailsFragment";
    private BookData.DataBean mBook;
    private NoteAdapter mNoteAdapter;
    private OthersDigestData digestData;
    public static List<OthersDigestData> mDataList = new ArrayList<>();//一定要记得右边的格式初始化！否则报空
    private int currentNumber;

    //以下名字组件前为中文名缩写。例如JJ为简介，JJ2为简介框
    private TextView mXQTextView;
    private ImageView mImageView,bookImageView;
    private TextView mSMTextView;
    private TextView mZZTextView;
    private TextView mJJTextView;
    private TextView mJJ2TextView;
    private Button mButton1;
    private Button mButton2;
    private RecyclerView mRecyclerView;

/*接收列表的数据并创建fragment，封装成方法*/
    public static BookDetailsFragment newInstance(int bookId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK_ID,bookId);

        BookDetailsFragment fragment = new BookDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //getRequest();
        /*获取列表的数据,即获得id，得到指定的book对象*/
        int bookId = (int) getArguments().getSerializable(ARG_BOOK_ID);
        //mBook = BookLab.get(getActivity()).getBook(bookId);
        mBook = new BookData().getBook(bookId,RecommendFragment.data);
        getRequest();
    }

/*创建视图*/
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fg_book_detail,container,false);
        //详情
        mXQTextView = (TextView)v.findViewById(R.id.book_detail);
        //书的图片
        bookImageView = (ImageView)v.findViewById(R.id.book_detail_pic);
        Glide.with(Objects.requireNonNull(getActivity())).load(mBook.getBook_picture()).into(bookImageView);
        Log.d(TAG,"the single pic is>>>>" + mBook.getBook_picture());
        //书名
        mSMTextView = (TextView)v.findViewById(R.id.book_detail_title);
        mSMTextView.setText(mBook.getBook_name());
        //作者
        mZZTextView = (TextView)v.findViewById(R.id.book_detail_writer);
        mZZTextView.setText(mBook.getBook_auther());
        //简介
        mJJTextView = (TextView)v.findViewById(R.id.book_detail_intro);
        //简介详情
        mJJ2TextView = (TextView)v.findViewById(R.id.book_detail_intro2);
        mJJ2TextView.setText(mBook.getBook_information());
        //添加书架
        mButton1 = (Button)v.findViewById(R.id.book_detail_shelf);
        //添加书摘
        mButton2 = (Button)v.findViewById(R.id.book_detail_add_note);
        //书摘列表
        mRecyclerView = (RecyclerView)v
                .findViewById(R.id.book_detail_list);//组件
        //updateUI();
        Log.d(TAG,"已经执行updateUI的方法了");

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //mRecyclerView.setLayoutManager(layoutManager);
        //mRecyclerView.setFocusable(false);

        return v;
    }

    private void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService api = retrofit.create(BookService.class);
        Call<List<OthersDigestData>> digestDataCall = api.getCall2("1");//mBook.getBook_id().toString()

        digestDataCall.enqueue(new Callback<List<OthersDigestData>>() {
            @Override
            public void onResponse(Call<List<OthersDigestData>> call, Response<List<OthersDigestData>> response) {
                Log.d(TAG,"onResponse----------" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json--------" + response.body().toString());
                    mDataList = response.body();
                    if (mDataList.size() != 0){
                        Log.d(TAG,"one of the list is  " + mDataList.get(0).toString());
                        updateUI();
                    }else{
                        Log.d(TAG,"暂无书摘！！");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OthersDigestData>> call, Throwable t) {
                Log.d(TAG,"error for note!!!!");
            }
        });
    }

/*关联RV和adapter*/
    private void updateUI(){
        //NotesLab notesLab = NotesLab.get(getActivity());
        //List<Notes> notes = notesLab.getNotes();

        mNoteAdapter = new NoteAdapter(mDataList);
        mNoteAdapter.notifyDataSetChanged();
        Log.d(TAG,"Adapter已经建立了"+mNoteAdapter.toString());

        mRecyclerView.setAdapter(mNoteAdapter);
        Log.d(TAG,"RecyclerView已经绑定Adapter了");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setFocusable(false);
    }

/*holder*/
    public class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RoundImageView mNoteImageView;
        private TextView mNoteName;
        private TextView mNoteDate;
        private TextView mNote;
        private OthersDigestData notes;

        public NoteHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.book_notes_list,parent,false));
            itemView.setOnClickListener(this);

            mNoteImageView = (RoundImageView) itemView.findViewById(R.id.comment_logo);
            mNoteName = (TextView)itemView.findViewById(R.id.comment_name);
            mNoteDate = (TextView)itemView.findViewById(R.id.comment_date);
            mNote = (TextView)itemView.findViewById(R.id.comment_content);
        }

        public void bind(OthersDigestData mNotes){
            notes = mNotes;
            mNoteName.setText(notes.getUser_id());
            Log.d(TAG,"这里说明bind方法已经被用了"+notes.getUser_id());
            mNote.setText(notes.getSummary_information());
            mNoteDate.setText(notes.getDate());
        }

        public void onClick(View view){
            currentNumber = getAbsoluteAdapterPosition();
            Log.d(TAG,"当前被点击的holder为"+currentNumber);
            Intent intent = OthersNoteActivity.newIntent(getContext(),currentNumber);
            startActivity(intent);
        }

    }

/*adapter*/
    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<OthersDigestData> mNotes;

        public NoteAdapter(List<OthersDigestData> notes){
            mNotes = notes;
        }
        
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        return new NoteHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        OthersDigestData notes = mNotes.get(position);
        Log.d(TAG,"看看有没有实行onBindViewHolder方法"+notes.toString());
        holder.bind(notes);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}

}