package com.example.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.BookExtract.BookExtratDetail;
import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.CommentDetail;
import com.example.library.data.CommentLab;
import com.example.library.data.MyDigest;

import java.net.HttpURLConnection;
import java.util.ArrayList;
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
    private List<MyDigest> myDigestList = new ArrayList<>();
    private MyDigestAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublicaion);

        textView1 = (TextView) findViewById(R.id.mydigest_title);
        recyclerView = (RecyclerView) findViewById(R.id.mydigest_recyclerView);
        textView2 = (TextView) findViewById(R.id.mydigest_change);
        get_DigestList();
    }

    public void UPDateUI() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyDigestAdapter(myDigestList, MyDigestActivity.this);
        adapter.setOnItemClickListener(new MyDigestAdapter.OnItemClickListener() {
            @Override
            public void onItemLongClick(View v, int position, int id) {
                PopupMenu popupMenu = new PopupMenu(MyDigestActivity.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.delete, popupMenu.getMenu());
                //弹出式菜单的菜单项点击事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        /**
                         * 删除的网络请求：（）里要填相应的是的id
                         */
                        adapter.notifyItemRemoved(position);
                        deleteDigest(id);
                        return true;
                    }
                });
                popupMenu.show();
            }

            @Override
            public void onItemClick(View v, int position, int id) {
                FragmentTransaction fragmentTransaction  = getSupportFragmentManager().beginTransaction();
                Fragment fragment  = BookExtratDetail.newInstance(id);
                fragmentTransaction.add(R.id.guide_fragment,fragment);
                fragmentTransaction.commit();
            }
        });
        recyclerView.setAdapter(adapter);
    }


    public static class MyDigestAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<MyDigest> myDigests;


        public MyDigestAdapter(List<MyDigest> list, Context context) {
            this.myDigests = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MyDigest myDigest = myDigests.get(position);
            holder.bind(myDigest);

            if (onItemClickListener != null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(holder.itemView, position, myDigest.getId());
                        return true;
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(holder.itemView, position, myDigest.getId());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return myDigests.size();
        }

        private MyDigestAdapter.OnItemClickListener onItemClickListener;

        public interface OnItemClickListener {
            void onItemLongClick(View v, int position, int id);

            void onItemClick(View v, int position, int id);
        }

        public void setOnItemClickListener(OnItemClickListener clickListener) {
            this.onItemClickListener = clickListener;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private MyDigest MyDigest;

        public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
            super(inflate.inflate(R.layout.item_my_publication, parent, false));
            imageView = itemView.findViewById(R.id.myNote_pic);
            textView1 = itemView.findViewById(R.id.myNote_name);
            textView2 = itemView.findViewById(R.id.myNote_Date);
            textView3 = itemView.findViewById(R.id.myNote_content);
        }

        public void bind(MyDigest myDigest) {
            MyDigest = myDigest;
            textView1.setText(MyDigest.getTitle());
            textView2.setText(MyDigest.getSummary_information());
            textView3.setText(MyDigest.getThought());

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

    public void deleteDigest(int digest_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<Response<Void>> user = userDate.deleteADigest(LoginActivity.token, digest_id);

        user.enqueue(new Callback<Response<Void>>() {


            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if (response.isSuccessful() == true) {
                    Toast.makeText(MyDigestActivity.this,
                            "删除书摘成功",
                            Toast.LENGTH_SHORT).show();
                }
                Log.d("MyDigestActivity", "删除单一书摘的网络请求成功");
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.d("MyBookActivity", "删除单一书摘的网络请求失败");
            }


        });
    }

    public void get_DigestList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<List<MyDigest>> mydigest = userDate.getDigest(LoginActivity.token);
        mydigest.enqueue(new Callback<List<MyDigest>>() {
            @Override
            public void onResponse(Call<List<MyDigest>> call, Response<List<MyDigest>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    myDigestList = response.body();
                    Log.d("MyDigestActivity", myDigestList.toString());
                    UPDateUI();
                }
                Toast.makeText(MyDigestActivity.this, "成功获取书摘", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<List<MyDigest>> call, Throwable t) {
                Toast.makeText(MyDigestActivity.this, "获取书摘失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
