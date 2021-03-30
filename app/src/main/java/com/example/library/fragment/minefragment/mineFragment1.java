package com.example.library.fragment.minefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.activity.MybookActivity;
import com.example.library.data.BookData;
import com.example.library.data.MyBook;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mineFragment1 extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private MyBookAdapt adapt;
    private List<MyBook> date;
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

        textView1 = (TextView) v.findViewById(R.id.a1_textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), MybookActivity.class);
                startActivity(intent1);
            }
        });

        textView2 = (TextView) v.findViewById(R.id.a1_textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Log.d("mineFragment1","mineFragment1使用前没有错误");
        Bundle bundle = getArguments();
        token = bundle.getString("mineFragment1");
        System.out.println(token+"     mineFragment1");
        get_MyBook("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTcyMDExMzcsImlhdCI6MTYxNjU5NjMzNywidXNlcl9pZCI6IjIiLCJ1c2VyX25hbWUiOiLpgrHkupHosaoiLCJ1c2VyX3Bhc3N3b3JkIjoiNjY2In0.M-xyTBUFJrlDAP5Zjd2yV95jpdrZp5lZJ0hfqnMfu6Y");
        Log.d("mineFragment1","mineFragment1网络请求后");

        return v;
    }
    public void UpUI(){
        adapt = new MyBookAdapt(date,getActivity());
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getActivity(),RecyclerView.HORIZONTAL,true));
        mRecyclerView.setAdapter(adapt);
    }

    class MyBookAdapt extends RecyclerView.Adapter<mineFragment1.MyBookAdapt.Holder> implements View.OnClickListener {

        private List<MyBook> mMyBook;
        private Context mContext;

        public MyBookAdapt(List<MyBook> myBook, Context context) {
            this.mMyBook = myBook;
            this.mContext = context;
        }

        @Override
        public mineFragment1.MyBookAdapt.Holder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());
            return new mineFragment1.MyBookAdapt.Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(mineFragment1.MyBookAdapt.Holder holder, int position) {
            MyBook myBook = mMyBook.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return mMyBook.size();
        }

        @Override
        public void onClick(View v) {

        }


        class Holder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView;

            public Holder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item_my_book, null, false));
                itemView.setOnClickListener((View.OnClickListener) this);
                imageView = (ImageView) itemView.findViewById(R.id.mybook_pic);
                textView = (TextView) itemView.findViewById(R.id.mybook_name);
            }

            public void bind(MyBook myBook) {

                textView.setText(myBook.getBook_name());
            }

        }
    }
    public void get_MyBook(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<MyBook> myBook = userDate.getBook(token);
        Log.d("mineFragment1","网络请求在此可运行1");
        myBook.enqueue(new Callback<MyBook>() {
            @Override
            public void onResponse(Call<MyBook> call, Response<MyBook> response) {
                Log.d("mineFragment1","网络请求在此可运行2");
                if (response.isSuccessful() == true) {
                    date = response.body().getMyBookDate();
                    UpUI();
                }
                Log.d("mineFragment1", "mineFragment1网络请求成功");
                Toast.makeText(getActivity(), "成功获取书籍", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<MyBook> call, Throwable t) {
                Toast.makeText(getActivity(), "获取书籍失败", Toast.LENGTH_SHORT).show();
                Log.d("mineFragment1","网络请求失败");
            }


        });
    }
    public static mineFragment1 newInstance(String token) {

        Bundle args = new Bundle();
        args.putString("mineFragment1", token);
        mineFragment1 fragment = new mineFragment1();
        fragment.setArguments(args);
        Log.d("mineFragment","可以传递");
        return fragment;
    }
}