package com.example.library.fragment.minefragment_son;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.BookExtract.BookExtratDetail;
import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.activity.LoginActivity;
import com.example.library.activity.MyDigestActivity;
import com.example.library.data.GetDigest;
import com.example.library.data.MyDigest;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class mineFragment2 extends Fragment {
    private RecyclerView nRecyclerView;
    private TextView textView;
    private LinearLayoutManager linearLayoutManager;

    private String time;
    private String time_1 = "yyyy-MM-dd HH:mm";

    private List<MyDigest> date = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_a2, container, false);
        textView = (TextView) v.findViewById(R.id.a2_textView1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), MyDigestActivity.class);
                startActivity(intent2);
            }
        });
        nRecyclerView = (RecyclerView) v.findViewById(R.id.mine_recycle2);
        get_Digest();


        return v;
    }

    public void upDateUI(){
        nRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        nRecyclerView.setLayoutManager(linearLayoutManager);
        nRecyclerView.setAdapter(new mineFragment2.MyNoteAdapter(date,getContext()));
    }

    public class MyNoteAdapter extends RecyclerView.Adapter<holder> {
        private List<MyDigest> mMyNotes;
        private Context mContext;


        public MyNoteAdapter(List<MyDigest> myNotes, Context context) {
            this.mMyNotes = myNotes;
            this.mContext = context;
        }

        @Override
        public holder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(holder mholder, int position) {
            MyDigest myNotes = mMyNotes.get(position);
            mholder.Bind(myNotes);
        }

        @Override
        public int getItemCount() {
            return mMyNotes.size();
        }
    }

    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private MyDigest digest;


        public holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_my_publication, parent, false));
            imageView = (ImageView) itemView.findViewById(R.id.myNote_pic);
            textView1 = (TextView) itemView.findViewById(R.id.myNote_name);
            textView2 = (TextView) itemView.findViewById(R.id.myNote_Date);
            textView3 = (TextView) itemView.findViewById(R.id.myNote_content);
        }


        public void Bind(MyDigest myNotes) {
            this.digest = myNotes;
            textView1.setText(digest.getTitle());
            textView3.setText(digest.getSummary_information());
            handler.post(updateTime);
        }

        /*实现日期读取*/
        Handler handler = new Handler();
        Runnable updateTime = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(updateTime, 1000);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time_1);
                time = simpleDateFormat.format(Calendar.getInstance().getTime());
                textView2.setText(time);
            }
        };

        @Override
        public void onClick(View v) {
            FragmentTransaction fragmentTransaction =((AppCompatActivity)getContext()).getSupportFragmentManager().beginTransaction();
            Fragment fragment = BookExtratDetail.newInstance(digest.getTitle());
            fragmentTransaction.add(R.id.guide_fragment,fragment);
            fragmentTransaction.commit();
        }
    }

    public void get_Digest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<List<MyDigest>> mydigest = userDate.getDigest(LoginActivity.token);
        Log.d("mineFragment2", "网络请求在此可运行+");
        mydigest.enqueue(new Callback<List<MyDigest>>() {
            @Override
            public void onResponse(Call<List<MyDigest>> call, Response<List<MyDigest>> response) {
                Log.d("mineFragment2", "网络请求在此可运行++");
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    date = response.body();
                    Log.d("mineFragment2", date.toString());
                    upDateUI();
                }
                Log.d("mineFragment2", "mineFragment2网络请求成功");
                Toast.makeText(getActivity(), "成功获取书摘", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<MyDigest>> call, Throwable t) {
                Toast.makeText(getActivity(), "获取书摘失败", Toast.LENGTH_SHORT).show();
                Log.d("mineFragment2", "网络请求失败");
            }


        });
    }
}
