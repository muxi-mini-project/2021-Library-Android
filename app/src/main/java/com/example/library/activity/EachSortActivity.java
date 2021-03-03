package com.example.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.data.Book;
import com.example.library.data.BookLab;

import java.util.List;

public class EachSortActivity extends AppCompatActivity {
    private static final java.lang.String EXTRA_SORT ="EachSortActivity" ;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private SortAdapter mAdapter;
    private java.lang.String SortName;

    public static Intent SortIntent(Context context, List<String> data, int position){
        Intent intent = new Intent(context,EachSortActivity.class);
        intent.putExtra(EXTRA_SORT,data.get(position).toString());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_each_sort_list);
        SortName = getIntent().getStringExtra(EXTRA_SORT);

        mTextView = (TextView) findViewById(R.id.each_sort_tv);
        mTextView.setText(SortName);
        mRecyclerView = (RecyclerView) findViewById(R.id.each_sort_recycler_view);
        updateUI();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void updateUI(){
        BookLab bookLab = BookLab.get(this);
        List<Book> books = bookLab.getBooks();

        mAdapter = new SortAdapter(books);
        mRecyclerView.setAdapter(mAdapter);
    }
}

class SortAdapter extends RecyclerView.Adapter<SortAdapter.SortHolder> {
    private List<Book> mBooks;

    protected SortAdapter(List<Book> books) {
        this.mBooks = books;
    }

    @NonNull
    @Override
    public SortHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SortHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SortHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

     class SortHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;
        private final TextView mTTTextView;
        private final TextView mWTTextView;
        private final TextView mInTextView;
        private Book mBook;

        public SortHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.son_fg_rm_each_book, parent, false));
            mImageView = (ImageView) itemView.findViewById(R.id.rm_book_pic);
            mTTTextView = (TextView) itemView.findViewById(R.id.rm_book_title);
            mWTTextView = (TextView) itemView.findViewById(R.id.rm_book_writer);
            mInTextView = (TextView) itemView.findViewById(R.id.rm_book_intro);
        }

        public void bind(Book book) {
            mBook = book;
            //mImageView.setImageResource(R.id.rm_book_pic);
            mTTTextView.setText(mBook.getBook_name());
            mWTTextView.setText(mBook.getBook_author());
            mInTextView.setText(mBook.getBook_information());
        }
    }
}