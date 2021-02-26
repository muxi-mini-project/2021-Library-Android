package com.example.library.fragment.sonfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.activity.BookDetailPagerActivity;
import com.example.library.R;
import com.example.library.data.Book;
import com.example.library.data.BookLab;
import com.example.library.fragment.BookCityFragment;

import java.util.List;

public class RecommendFragment extends BookCityFragment {
    private static final String TAG = "RF";
    private RecyclerView mRMRecyclerView;
    private BookAdapter mAdapter;
    private static int itemPosition;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.son_fg_recycler,container,false);
        Log.d(TAG,"View is created here.");

        //实例化RecyclerView视图
        mRMRecyclerView = (RecyclerView) view
                .findViewById(R.id.son_fg_recycler_view);
        updateUI();
        mRMRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        Log.d(TAG,"RecyclerView is created here?");


        return view;
    }

/*关联Adapter和RV*/
    private void updateUI(){

        BookLab bookLab = BookLab.get(getParentFragment().getActivity());
        List<Book> books = bookLab.getBooks();

        mAdapter = new BookAdapter(books);
        mRMRecyclerView.setAdapter(mAdapter);
    }

 /*holder*/
    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
     private ImageView mImageView;
     private TextView mTTTextView;
     private TextView mWTTextView;
     private TextView mInTextView;
     public Book mBook;

     public BookHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.son_fg_rm_each_book,null,false));
            itemView.setOnClickListener(this);
            //构造就实例化组件
            mImageView = (ImageView) itemView.findViewById(R.id.rm_book_pic);
            mTTTextView = (TextView) itemView.findViewById(R.id.rm_book_title);
            mWTTextView = (TextView) itemView.findViewById(R.id.rm_book_writer);
            mInTextView = (TextView) itemView.findViewById(R.id.rm_book_intro);
     }

        //@SuppressLint("ResourceType")
        public void bind(Book book){
            mBook = book;
            //mImageView.setImageResource(R.id.rm_book_pic);
            mTTTextView.setText(mBook.getBook_name());
            mWTTextView.setText(mBook.getBook_author());
            mInTextView.setText(mBook.getBook_information());
        }

        //点击事件。别忘了接口
        @Override
        public void onClick(View view){
            Intent intent = BookDetailPagerActivity.newIntent(getActivity(),mBook.getId());
            itemPosition = getBindingAdapterPosition();
            startActivity(intent);
        }
    }

 /*adapter*/
    private class BookAdapter extends RecyclerView.Adapter<BookHolder>{
        private List<Book> mBooks;

        public BookAdapter(List<Book> books){
            mBooks = books;
        }

    /*创建一个。给布局，实例化*/
         @Override
         public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());

            return new BookHolder(layoutInflater,parent);
         }

    /*绑一个。给它更新*/
         @Override
         public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            Book book = mBooks.get(position);
            holder.bind(book);
         }

    /*到头*/
         @Override
         public int getItemCount() {
             return mBooks.size();
         }
 }

    @Override
    public void onPause(){
        super.onPause();
    }
}
