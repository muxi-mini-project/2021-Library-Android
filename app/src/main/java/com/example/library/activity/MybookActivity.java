package com.example.library.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.transition.Transition;
import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.MyBook;
import com.example.library.data.Users;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MybookActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private RecyclerView recyclerView;
    private Context context;
    private LinearLayoutManager linearLayoutManager;

    public final static int F1 = 0xeff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView) findViewById(R.id.my_book_title);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerView1);
        textView2 = (TextView) findViewById(R.id.my_book_change);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new GridLayoutManager(MybookActivity.this, 4);
        recyclerView.setLayoutManager(linearLayoutManager);
        BookLab bookLab = BookLab.get(MybookActivity.this);
        List<MyBook> mybook = bookLab.getmMyBooks();
        recyclerView.setAdapter(new MybookActivity.MybookAdapter(mybook, MybookActivity.this));

    }


    public class MybookAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<MyBook> myBookList;


        public MybookAdapter(List<MyBook> list, Context context) {
            this.myBookList = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(MybookActivity.this);
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyBook myBook = myBookList.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return myBookList.size();
        }


    }


    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        private MyBook mMyBook;


        public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
            super(inflate.inflate(R.layout.item_my_book, parent, false));
            imageView = (ImageView) itemView.findViewById(R.id.mybook_pic);
            textView = (TextView) itemView.findViewById(R.id.mybook_name);
            itemView.setOnClickListener(this);
            Log.d("MyBookActivity", "点击事件设置");
        }

        public void bind(MyBook myBook) {
            mMyBook = myBook;
            textView.setText(mMyBook.getBook_name());

        }

        @Override
        public void onClick(View v) {
            Log.d("MyBookActivity", "成功点击");
            Toast.makeText(MybookActivity.this, "点击成功", Toast.LENGTH_SHORT);
        }
    }

    public void getBookDetail(String token, int book_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<MyBook> user = userDate.getABook(token, book_id);

        user.enqueue(new Callback<MyBook>() {


            @Override
            public void onResponse(Call<MyBook> call, Response<MyBook> response) {
                if (response.isSuccessful() == true) {

                }
                Log.d("MyBookActivity", "获取单一书本的网络请求成功");
            }

            @Override
            public void onFailure(Call<MyBook> call, Throwable t) {
                Log.d("MyBookActivity", "获取单一书本的网络请求失败");
            }


        });
    }

    public void deleteBookDetail(String token, int book_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<Response<Void>> user = userDate.deleteABook(token, book_id);

        user.enqueue(new Callback<Response<Void>>() {


            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if (response.isSuccessful() == true) {
                    Toast.makeText(MybookActivity.this, "删除书本成功", Toast.LENGTH_SHORT).show();
                }
                Log.d("MyBookActivity", "删除单一书本的网络请求成功");
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.d("MyBookActivity", "删除单一书本的网络请求失败");
            }


        });
    }

}