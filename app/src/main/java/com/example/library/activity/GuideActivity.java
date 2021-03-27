package com.example.library.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.Interface.BookService;
import com.example.library.Interface.LoginService;
import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.data.BookData;
import com.example.library.data.User;
import com.example.library.data.Users;
import com.example.library.fragment.BookCityFragment;
import com.example.library.fragment.ChoseBookExtract;
import com.example.library.fragment.mineFragment;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GuideActivity";
    //public static List<BookData.DataBean> DATA = new ArrayList<>();

    /*底部导航栏的文字部分*/
    private ImageView book_city;//书城
    private ImageView digest;//书摘
    private ImageView mine;//我的

    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private FrameLayout fg_content;//中间的fragment部分的视图

    /*底部导航栏对应的Fragment*/
    private BookCityFragment fragment1;
    private ChoseBookExtract fragment2;
    private mineFragment fragment3;

    private String user_name;
    private String user_picture;
    private String user_motto;
    private String user_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_guide);//此处为绿色原版
        //getRequest();
        Log.d(TAG,"书城的activity");
        bindView();
        linearLayout1.performClick();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_token = bundle.getString("getToken");
        System.out.println(user_token);
        //Log.d(TAG,"在activity内查看DATA的数据"+DATA.toString());
    }
    /*public void getRequest(){
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
                Log.d(TAG,"onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json>>>>>" + response.body().toString());
                    DATA = response.body().getData();
                    Log.d(TAG,"data--------------" + DATA.toString());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BookData> call, Throwable t)
            {
                Log.d(TAG,"error ---");
            }
        });
    }*/



    /*将实例事件与ui视图绑定*/
    private void bindView() {
        book_city = (ImageView) findViewById(R.id.txt_book_city);
        digest = (ImageView) findViewById(R.id.txt_digest);
        mine = (ImageView) findViewById(R.id.txt_mine);
        fg_content = (FrameLayout) findViewById(R.id.fg_content);

        linearLayout1 = (LinearLayout) findViewById(R.id.booK_city_all);
        linearLayout2 = (LinearLayout) findViewById(R.id.digest_all);
        linearLayout3 = (LinearLayout) findViewById(R.id.mine_all);

        /*设置监听器,使其变为可点击事件*/
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);

    }

    /*重置文本点击状态*/
    private void setSelect(){
        linearLayout1.setSelected(false);
        linearLayout2.setSelected(false);
        linearLayout3.setSelected(false);
    }

    @Override
    public void onClick(View v) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        setSelect();
        switch (v.getId()){
            case R.id.booK_city_all:
                linearLayout1.setSelected(true);
                if(fragment1 == null){
                    fragment1 = new BookCityFragment();
                    fragmentTransaction.add(R.id.guide_fragment,fragment1);
                }
                else
                {
                    fragmentTransaction.show(fragment1);
                }
                break;
            case R.id.digest_all:
                linearLayout2.setSelected(true);
                if(fragment2 == null){
                    fragment2 = new ChoseBookExtract();
                    fragmentTransaction.add(R.id.guide_fragment,fragment2);
                }
                else
                {
                    fragmentTransaction.show(fragment2);
                }
                break;
            case R.id.mine_all:
                linearLayout3.setSelected(true);
                Get_user_Date(user_token);
                fragment3 = mineFragment.newInstance(user_name,user_picture,user_motto);

                /**
                 *在这里使用newInstance后返回白板
                 */

                if(fragment3 == null){
                    fragment3 = new mineFragment();
                    fragmentTransaction.add(R.id.guide_fragment,fragment3);
                }
                else
                {
                    fragmentTransaction.show(fragment3);
                }
                break;

        }
        fragmentTransaction.commit();

    }

    /*隐藏所有的fragment(目前没想明白干啥用的)*/
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if(fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
        if(fragment3 != null)
            fragmentTransaction.hide(fragment3);
    }

    public void Get_user_Date(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<Users> user= userDate.getCall(token);
        user.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful() == true) {
                    user_picture = response.body().getPicture();
                    System.out.println(user_picture);
                    user_name = response.body().getUser_name();
                    System.out.println(user_name);
                    user_motto = response.body().getMotto();
                    Toast.makeText(GuideActivity.this, "成功获取信息", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(GuideActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(GuideActivity.this, "GG", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void getRequest(){
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
                Log.d(TAG,"onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json>>>>>" + response.body().toString());
                    DATA = response.body().getData();
                    Log.d(TAG,"data--------------" + DATA.toString());
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BookData> call, Throwable t)
            {
                Log.d(TAG,"error ---");
            }
        });
    }*/

}