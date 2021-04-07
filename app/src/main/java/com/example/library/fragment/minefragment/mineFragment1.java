package com.example.library.fragment.minefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.activity.LoginActivity;
import com.example.library.activity.MybookActivity;
import com.example.library.data.BookData;
import com.example.library.data.MyBook;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.Integer.valueOf;

public class mineFragment1 extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private MyBookAdapt adapt;
    private List<MyBook> date = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private String token;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a1, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.mine_recycle1);
        get_MyBook();
        textView1 = (TextView) v.findViewById(R.id.a1_textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MybookActivity.class);
                startActivity(intent1);
            }
        });


        return v;
    }

    public void UpUI() {
        adapt = new MyBookAdapt(date, getActivity());
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity(), RecyclerView.HORIZONTAL, true));
        mRecyclerView.setAdapter(adapt);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
    }

    public class MyBookAdapt extends RecyclerView.Adapter<Holder> implements View.OnClickListener {

        private List<MyBook> mMyBook;
        private Context mContext;

        public MyBookAdapt(List<MyBook> myBook, Context context) {
            this.mMyBook = myBook;
            this.mContext = context;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());
            return new Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyBook myBook = mMyBook.get(position);
            Log.d("mineFragment1", "mineFragment1中的数据" + myBook.toString());
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            if (mMyBook != null) {
                return mMyBook.size();
            }
            return 0;
        }

        @Override
        public void onClick(View v) {

        }

    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;
        public MyBook mMyBook;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_my_book, null, false));
            itemView.setOnClickListener(this);
            imageView = (ImageView) itemView.findViewById(R.id.mybook_pic);
            textView = (TextView) itemView.findViewById(R.id.mybook_name);
        }

        public void bind(MyBook myBook) {
            mMyBook = myBook;
            textView.setText(mMyBook.getBook_name());
//            imageView.setImageResource(Integer.parseInt(mMyBook.getBook_picture()));
        }

        @Override
        public void onClick(View v) {

        }
    }


    public void get_MyBook() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<List<MyBook>> myBook = userDate.getBook(LoginActivity.token);
        Log.d("mineFragment1", "网络请求在此可运行1");
        myBook.enqueue(new Callback<List<MyBook>>() {
            @Override
            public void onResponse(Call<List<MyBook>> call, Response<List<MyBook>> response) {
                Log.d("mineFragment1", "网络请求在此可运行2");
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //date = response.body().getData();
                    date = response.body();
                    Log.d("mineFragment1", date.toString());
                    getPicData(date);
                    UpUI();
                }
                Log.d("mineFragment1", "mineFragment1网络请求成功");
                Toast.makeText(getActivity(), "成功获取书籍", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<MyBook>> call, Throwable t) {
                Toast.makeText(getActivity(), "获取书籍失败", Toast.LENGTH_SHORT).show();
                Log.d("mineFragment1", "网络请求失败");
            }


        });
    }

    /*获取图片的地址数组*/
    public static List<String> getPicData(List<MyBook> data) {
        List<String> pics = new ArrayList<>();
        for (MyBook a : data) {
            pics.add(a.getBook_picture());
        }
        return pics;
    }


    public static mineFragment1 newInstance(String token) {

        Bundle args = new Bundle();
        args.putString("mineFragment1", token);
        mineFragment1 fragment = new mineFragment1();
        fragment.setArguments(args);
        Log.d("mineFragment", "可以传递");
        return fragment;
    }
}