package com.example.library.fragment.sonfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.library.Interface.BookService;
import com.example.library.R;
import com.example.library.activity.BookDetailPagerActivity;
import com.example.library.activity.GuideActivity;
import com.example.library.data.BookData;
import com.example.library.fragment.BookCityFragment;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendFragment extends BookCityFragment {
    private static final String TAG = "RF";
    public static List<BookData.DataBean> data = new ArrayList<>();
    public static List<String> pics = new ArrayList<>();
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
        //pics = getPicData(data);

        //实例化RecyclerView视图
        mRMRecyclerView = (RecyclerView) view
                .findViewById(R.id.son_fg_recycler_view);
        getRequest();
        Log.d(TAG,"RecyclerView is here!!!!");
        return view;
    }

    public void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService mApi = retrofit.create(BookService.class);
        Call<BookData> bookDataCall = mApi.getCall();

        bookDataCall.enqueue(new Callback<BookData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                Log.d(TAG,"推荐的onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    //Log.d(TAG,"Json>>>>>" + response.body().toString());
                    data = response.body().getData();
                    //Log.d(TAG,"data--------------" + data.toString());
                    pics = getPicData(data);
                    updateUI();
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BookData> call, Throwable t)
            {
            Log.d(TAG,"error ---");
            }
        });
    }
/*获取图片的地址数组*/
    public static List<String> getPicData(List<BookData.DataBean> data){
        List<String> pics = new ArrayList<>();
        for (BookData.DataBean a:data){
            pics.add(a.getBook_picture());
        }
        return pics;
    }

/*关联Adapter和RV*/
    private void updateUI(){
        //BookLab bookLab = BookLab.get(getParentFragment().getActivity());
        //List<Book> books = bookLab.getBooks();
        mAdapter = new BookAdapter(getActivity(),data);
        mRMRecyclerView.setAdapter(mAdapter);
        mRMRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity()));
        //Log.d(TAG,"adapter已经设置好了"+mAdapter.toString());
    }

    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
     private ImageView mImageView;
     private TextView mTTTextView;
     private TextView mWTTextView;
     private TextView mInTextView;
     public BookData.DataBean mBook;

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
        public void bind(BookData.DataBean book){
            mBook = book;
            //Log.d(TAG,"user id is aaaaaaaaaaaaa"+book.getBook_auther());
            mTTTextView.setText(mBook.getBook_name());
            mWTTextView.setText(mBook.getBook_auther());
            mInTextView.setText(mBook.getBook_information());
        }

        //点击事件。别忘了接口
        @Override
        public void onClick(View view){
            Intent intent = BookDetailPagerActivity.newIntent(getActivity(),mBook.getBook_id());
            itemPosition = getBindingAdapterPosition();
            Log.e(TAG,"传过去的书的id是！！！"+mBook.getBook_id());
            Toast.makeText(getActivity(),"可左右滑动切换书籍",Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }

    private class BookAdapter extends RecyclerView.Adapter<BookHolder>{
        private List<BookData.DataBean> mBooks;
        private Context mContext;

        public BookAdapter(Context context,List<BookData.DataBean> books){
            mContext = context;
            mBooks = books;
        }

         @Override
         public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());

            return new BookHolder(layoutInflater,parent);
         }

         @Override
         public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            BookData.DataBean book = mBooks.get(position);
            //Log.d(TAG,"bind的数据" + book.toString());
            holder.bind(book);
            //Log.d(TAG,"the picture is" + pics.get(position));
            Glide.with(mContext).load(pics.get(position)).into(holder.mImageView);
         }

         @Override
         public int getItemCount() {
            if (mBooks!=null){
                return mBooks.size();
            }
             return 1;
         }
    }

}
