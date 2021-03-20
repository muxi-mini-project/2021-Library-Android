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
import com.example.library.data.BookData;
import com.example.library.data.BookLab;
import com.example.library.fragment.BookCityFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RankFragment extends BookCityFragment {
    private static final String TAG = "RankFragment" ;
    private RecyclerView mRankRecyclerView;
    private BookAdapter2 mAdapter;
    private static int itemPosition;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.son_fg_recycler, null, false);

        mRankRecyclerView = (RecyclerView) view
                .findViewById(R.id.son_fg_recycler_view);
        updateUI();
        mRankRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));

        return view;
    }


    private void updateUI(){
        //BookLab bookLab = BookLab.get(getParentFragment().getActivity());
        //List<DataBean> books = bookLab.getBooks();
        mAdapter = new BookAdapter2(RecommendFragment.data);
        mRankRecyclerView.setAdapter(mAdapter);
    }

    private class BookHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;
        private TextView mTTTextView;
        private TextView mWTTextView;
        private TextView mInTextView;
        public BookData.DataBean mBook;

        public BookHolder2(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.son_fg_rm_each_book,null,false));
            itemView.setOnClickListener(this);

            mImageView = (ImageView) itemView.findViewById(R.id.rm_book_pic);
            mTTTextView = (TextView) itemView.findViewById(R.id.rm_book_title);
            mWTTextView = (TextView) itemView.findViewById(R.id.rm_book_writer);
            mInTextView = (TextView) itemView.findViewById(R.id.rm_book_intro);
        }


        public void bind(BookData.DataBean book){
            mBook = book;
            mTTTextView.setText(mBook.getBook_name());
            mWTTextView.setText(mBook.getBook_auther());
            mInTextView.setText(mBook.getBook_information());
        }

        public void onClick(View view) {
            Intent intent = BookDetailPagerActivity.newIntent(getActivity(), mBook.getBook_id());
            itemPosition =getBindingAdapterPosition();
            startActivity(intent);
        }
    }


    private class BookAdapter2 extends RecyclerView.Adapter<BookHolder2> {
        private List<BookData.DataBean> mBooks;

        public BookAdapter2(List<BookData.DataBean> books) {
            mBooks = books;
        }

        @NonNull
        @Override
        public BookHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());

            return new BookHolder2(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookHolder2 holder, int position) {
            BookData.DataBean book = mBooks.get(position);
            holder.bind(book);
            List<String> pics2 = RecommendFragment.getPicData(RecommendFragment.data);
            if (pics2.size() == 0){
                Log.d(TAG,"The pics is null!!!!!!");
            }else {
                Glide.with(getParentFragment().getActivity()).load(pics2.get(position)).into(holder.mImageView);
            }
            //Log.d(TAG,"the gained pic is >>>" + pics2.get(position));
        }

        @Override
        public int getItemCount() {
            return mBooks.size();
        }
    }

}
