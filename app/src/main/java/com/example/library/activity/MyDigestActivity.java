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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.CommentDetail;
import com.example.library.data.CommentLab;
import com.example.library.data.MyBook;
import com.example.library.data.MyDigest;

import org.w3c.dom.Comment;

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
        recyclerView.setAdapter(new MyDigestActivity.MybookAdapter(commentDetails, MyDigestActivity.this));


    }


    class MybookAdapter extends RecyclerView.Adapter<MyDigestActivity.MybookAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<CommentDetail> CommentDetail;


        public MybookAdapter(List<CommentDetail> list, Context context) {
            this.CommentDetail = list;
            this.context = context;
        }

        @Override
        public MyDigestActivity.MybookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(MyDigestActivity.this);
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
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


            public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
                super(inflate.inflate(R.layout.item_my_publication, parent, false));
                imageView = itemView.findViewById(R.id.mybook_pic);
                textView1 = itemView.findViewById(R.id.mybook_name);



            }

            public void bind(CommentDetail commentDetail) {
                textView1.setText(commentDetail.getCommentName());

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
}
