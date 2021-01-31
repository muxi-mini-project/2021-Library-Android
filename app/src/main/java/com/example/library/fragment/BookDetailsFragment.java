package com.example.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.data.Book;
import com.example.library.data.BookLab;
import com.example.library.data.Notes;
import com.example.library.data.NotesLab;

import java.util.List;
import java.util.UUID;

public class BookDetailsFragment extends Fragment {
    private static final String ARG_BOOK_ID = "book_id";
    private Book mBook;
    private NoteAdapter mNoteAdapter;

    //以下名字组件前为中文名缩写。例如JJ为简介，JJ2为简介框
    private TextView mXQTextView;
    private ImageView mImageView;
    private TextView mSMTextView;
    private TextView mZZTextView;
    private TextView mJJTextView;
    private TextView mJJ2TextView;
    private Button mButton1;
    private Button mButton2;
    private RecyclerView mRecyclerView;

/*接收列表的数据并创建fragment，封装成方法*/
    public static BookDetailsFragment newInstance(UUID bookId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK_ID,bookId);

        BookDetailsFragment fragment = new BookDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
/*获取列表的数据,即获得id，得到指定的book对象*/
        UUID bookId = (UUID) getArguments().getSerializable(ARG_BOOK_ID);
        mBook = BookLab.get(getActivity()).getBook(bookId);

    }

/*创建视图*/
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fg_book_detail,container,false);
        //详情
        mXQTextView = (TextView)v.findViewById(R.id.book_detail);
        //书名
        mSMTextView = (TextView)v.findViewById(R.id.book_detail_title);
        mSMTextView.setText(mBook.getBookTitle());
        //作者
        mZZTextView = (TextView)v.findViewById(R.id.book_detail_writer);
        mZZTextView.setText(mBook.getBookWriter());
        //简介
        mJJTextView = (TextView)v.findViewById(R.id.book_detail_intro);
        //简介详情
        mJJ2TextView = (TextView)v.findViewById(R.id.book_detail_intro2);
        mJJ2TextView.setText(mBook.getIntroduction());
        //添加书架
        mButton1 = (Button)v.findViewById(R.id.book_detail_shelf);
        //添加书摘
        mButton2 = (Button)v.findViewById(R.id.book_detail_add_note);
        //书摘列表
        mRecyclerView = (RecyclerView)v
                .findViewById(R.id.book_detail_list);//组件
        updateUI();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        return v;
    }

/*关联RV和adapter*/
    private void updateUI(){
        NotesLab notesLab = NotesLab.get(getActivity());
        List<Notes> notes = notesLab.getNotes();

        mNoteAdapter = new NoteAdapter(notes);
        mRecyclerView.setAdapter(mNoteAdapter);
    }

/*holder*/
    public class NoteHolder extends RecyclerView.ViewHolder{
        private ImageView mNoteImageView;
        private TextView mNoteName;
        private TextView mNoteDate;
        private TextView mNote;

        public NoteHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.book_notes_list,parent,false));

            mImageView = (ImageView)itemView.findViewById(R.id.book_detail_note_pic);
            mNoteName = (TextView)itemView.findViewById(R.id.book_detail_name);
            mNoteDate = (TextView)itemView.findViewById(R.id.book_detail_date);
            mNote = (TextView)itemView.findViewById(R.id.book_detail_note_content);
        }

        public void bind(Notes notes){
            mNoteName.setText(notes.getNoteWriter());
            mNote.setText(notes.getNoteContent());
            mNoteDate.setText(notes.getNoteDate());
        }

    }

/*adapter*/
    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<Notes> mNotes;

        public NoteAdapter(List<Notes> notes){
            mNotes = notes;
        }

//要去理解一下是什么意思了
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        return new NoteHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Notes notes = mNotes.get(position);
        holder.bind(notes);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}

/*禁止RV滑动
    public class CustomLinearLayoutManager extends LinearLayoutManager {
        private boolean isScrollEnabled = false;

        public CustomLinearLayoutManager(Context context) {
            super(context);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }
    }*/
}