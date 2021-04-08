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

import com.bumptech.glide.Glide;
import com.example.library.Interface.BookService;
import com.example.library.activity.BookDetailPagerActivity;
import com.example.library.R;
import com.example.library.data.BookData;
import com.example.library.data.BookLab;
import com.example.library.fragment.BookCityFragment;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.library.fragment.sonfragment.RecommendFragment.getPicData;

public class RankFragment extends BookCityFragment {
    private static final String TAG = "RankFragment" ;
    private RecyclerView mRankRecyclerView;
    private BookAdapter2 mAdapter;
    private static int itemPosition;
    public static List<BookData.DataBean> data2 = new ArrayList<>();
    private List<String> pics2;


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
        getRequest();

        return view;
    }

    public void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService mApi = retrofit.create(BookService.class);
        Call<BookData> bookDataCall = mApi.getRankCall();

        bookDataCall.enqueue(new Callback<BookData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                Log.d(TAG,"onResponse>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json>>" + response.body().toString());
                    data2 = response.body().getData();
                    Log.d(TAG,"data--" + data2.toString());
                    pics2 = getPicData(data2);
                    updateUI();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BookData> call, Throwable t)
            {
                Log.d(TAG,"排行error ---");
            }
        });
    }



    private void updateUI(){
        mAdapter = new BookAdapter2(data2);
        Log.d(TAG,"排行的数据有没有得到"+data2.toString());
        mRankRecyclerView.setAdapter(mAdapter);
        mRankRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
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
            Log.d(TAG,"排行的user id is "+book.getBook_auther());

        }

        public void onClick(View view) {
            Intent intent = BookDetailPagerActivity.newIntent(getActivity(), mBook.getBook_id());
            Log.e(TAG,"排行的    "+mBook.getBook_id());
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
            if (pics2.size() == 0){
                Log.d(TAG,"The pics is null!!!!!!");
            }else {
                Glide.with(getParentFragment().getActivity()).load(pics2.get(position)).into(holder.mImageView);
            }
            Log.d(TAG,"the gained pic is >>>" + pics2.get(position));
        }

        @Override
        public int getItemCount() {
            return mBooks.size();
        }
    }

}

