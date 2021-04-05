package com.example.library.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.CommentDetail;
import com.example.library.data.CommentLab;
import com.example.library.data.MyDigest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyDigestActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private RecyclerView recyclerView;
    private Context context;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublicaion);

        textView1 = (TextView) findViewById(R.id.mydigest_title);
        recyclerView = (RecyclerView) findViewById(R.id.mydigest_recyclerView);
        textView2 = (TextView) findViewById(R.id.mydigest_change);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        CommentLab commentLab = CommentLab.get(MyDigestActivity.this);
        List<CommentDetail> commentDetails = commentLab.getCommentDetailList();
        recyclerView.setAdapter(new MyDigestActivity.MyDigestAdapter(commentDetails, MyDigestActivity.this));


    }


    public class MyDigestAdapter extends RecyclerView.Adapter<MyDigestActivity.MyDigestAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<CommentDetail> CommentDetail;


        public MyDigestAdapter(List<CommentDetail> list, Context context) {
            this.CommentDetail = list;
            this.context = context;
        }

        @Override
        public MyDigestActivity.MyDigestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(MyDigestActivity.this);
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyDigestActivity.MyDigestAdapter.ViewHolder holder, int position) {

            CommentDetail commentDetail =CommentDetail.get(position);
            holder.bind(commentDetail);
            holder.imageView.setOnClickListener(this);
            holder.imageView.setOnLongClickListener(this);
        }

        @Override
        public int getItemCount() {
            return CommentDetail.size();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MyDigestActivity.this, "单击", Toast.LENGTH_SHORT);
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MyDigestActivity.this, "长按", Toast.LENGTH_SHORT);
            return true;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;
            private CommentDetail commentDetails;


            public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
                super(inflate.inflate(R.layout.item_my_publication, parent, false));
                imageView = itemView.findViewById(R.id.mybook_pic);
                textView1 = itemView.findViewById(R.id.mybook_name);



            }

            public void bind(CommentDetail commentDetail) {
                commentDetails = commentDetail;
                textView1.setText(commentDetails.getCommentName());
                textView2.setText(commentDetails.getCommentDate());
                textView3.setText(commentDetails.getCommentDate());

            }
        }
    }

    public void getDigest(String token, int book_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<MyDigest> user = userDate.getADigest(token, book_id);

        user.enqueue(new Callback<MyDigest>() {


            @Override
            public void onResponse(Call<MyDigest> call, Response<MyDigest> response) {
                if (response.isSuccessful() == true) {

                }
                Log.d("MyBookActivity", "获取单一书摘的网络请求成功");
            }

            @Override
            public void onFailure(Call<MyDigest> call, Throwable t) {
                Log.d("MyBookActivity", "获取单一书摘的网络请求失败");
            }


        });
    }

    public void deleteDigest(String token,int digest_id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<Response<Void>> user = userDate.deleteADigest(token, digest_id);

        user.enqueue(new Callback<Response<Void>>() {


            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if (response.isSuccessful() == true) {
                    Toast.makeText(MyDigestActivity.this,"删除书摘成功",Toast.LENGTH_SHORT).show();
                }
                Log.d("MyDigestActivity", "删除单一书摘的网络请求成功");
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.d("MyBookActivity", "删除单一书摘的网络请求失败");
            }


        });
    }
}
