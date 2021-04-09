package com.example.library.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.MyBook;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    private MybookAdapter adapter;
    private static Bitmap pic2;
    private List<MyBook> date = new ArrayList<>();

    public final static int F1 = 0xefc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView) findViewById(R.id.my_book_title);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerView1);
        textView2 = (TextView) findViewById(R.id.my_book_change);
        get_MyBookList();


    }

    public void UpDateUI() {
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new GridLayoutManager(MybookActivity.this, 4);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MybookAdapter(date, MybookActivity.this);
        adapter.setOnItemClickListener(new MybookAdapter.OnItemClickListener() {
                                           @Override
            /**
             *长按弹出“删除”
             */

            public void onItemLongClick(final View view, final int pos,final int id) {
                                               PopupMenu popupMenu = new PopupMenu(MybookActivity.this, view);
                                               popupMenu.getMenuInflater().inflate(R.menu.delete, popupMenu.getMenu());

                                               //弹出式菜单的菜单项点击事件
                                               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                                                   @Override
                                                   public boolean onMenuItemClick(MenuItem item) {
                                                       /**
                                                        * 删除的网络请求：（）里要填相应的是的id
                                                        */
                                                       adapter.notifyItemRemoved(pos);
                                                       deleteBookDetail(id);
                                                       return true;
                                                   }
                                               });
                                               popupMenu.show();
                                           }

            /**
             * 单击跳转，与上面问题相同
             * @param position
             */
            @Override
            public void omItemClick(final View view,final int position,final int id) {
                                               Intent intent = BookDetailPagerActivity.newIntent(MybookActivity.this,id);
                                               startActivity(intent);
                                           }
                                       });
        recyclerView.setAdapter(adapter);
    }

    public static class MybookAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private List<MyBook> myBookList;


        public MybookAdapter(List<MyBook> list, Context context) {
            this.myBookList = list;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyBook myBook = myBookList.get(position);
            holder.bind(myBook);
            /**
             * 导入图片
             */
            Handler handler = new Handler() {
                @SuppressLint("HandlerLeak")
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 0x1234) {
                        holder.imageView.setImageBitmap(pic2);
                    }
                }
            };

            new Thread(new Runnable() {
                @Override
                public void run() {

                    URL url = null;
                    try {
                        url = new URL(String.valueOf(myBook.getBook_picture()));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    InputStream is = null;
                    try {
                        is = url.openStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pic2 = BitmapFactory.decodeStream(is);
                    handler.sendEmptyMessage(0x1234);
                }
            }).start();






/**
 * 监听器设置
 */
            if(onItemClickListener!=null) {
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(holder.itemView,position,myBook.getBook_id());
                        return true;
                    }
                });
                holder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.omItemClick(holder.itemView,position,myBook.getBook_id());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return myBookList.size();
        }

        private OnItemClickListener onItemClickListener;

        public interface OnItemClickListener {
            void onItemLongClick(View v, int position,int id);

            void omItemClick(View v,int position,int id);
        }

        public void setOnItemClickListener(OnItemClickListener clickListener) {
            this.onItemClickListener = clickListener;
        }
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private MyBook mMyBook;


        public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
            super(inflate.inflate(R.layout.item_my_book, parent, false));
            imageView = (ImageView) itemView.findViewById(R.id.mybook_pic);
            textView = (TextView) itemView.findViewById(R.id.mybook_name);
        }

        public void bind(MyBook myBook) {
            mMyBook = myBook;
            textView.setText(mMyBook.getBook_name());

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

    public void deleteBookDetail(int book_id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        Call<Response<Void>> user = userDate.deleteABook(LoginActivity.token, book_id);

        user.enqueue(new Callback<Response<Void>>() {


            @Override
            public void onResponse(Call<Response<Void>> call, Response<Response<Void>> response) {
                if (response.isSuccessful() == true) {
                    Toast.makeText(MybookActivity.this, "删除书本成功", Toast.LENGTH_SHORT).show();
                }
                Log.d("MyBookActivity", "删除单一书本的网络请求成功"+",删除的书本id是"+book_id);
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.d("MyBookActivity", "删除单一书本的网络请求失败");
            }


        });
    }

    public void get_MyBookList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<List<MyBook>> myBook = userDate.getBook(LoginActivity.token);
        myBook.enqueue(new Callback<List<MyBook>>() {
            @Override
            public void onResponse(Call<List<MyBook>> call, Response<List<MyBook>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //date = response.body().getData();
                    date = response.body();
                    Log.d("MyBookActivity", date.toString());
                    UpDateUI();
                }
                Toast.makeText(MybookActivity.this, "成功获取书籍", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<MyBook>> call, Throwable t) {
                Toast.makeText(MybookActivity.this, "获取书籍失败", Toast.LENGTH_SHORT).show();
            }


        });

    }
}