package com.example.library.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.fragment.BookCityFragment;
import com.example.library.fragment.MineFragment;
import com.example.library.fragment.ChoseBookExtract;
import com.example.library.fragment.mineFragment;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_guide);//此处为绿色原版
        bindView();
        linearLayout1.performClick();
    }


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
                    fragmentTransaction.add(R.id.fragment,fragment1);
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
                    fragmentTransaction.add(R.id.fragment,fragment2);
                }
                else
                {
                    fragmentTransaction.show(fragment2);
                }
                break;
            case R.id.mine_all:
                linearLayout3.setSelected(true);
                if(fragment3 == null){
                    fragment3 = new mineFragment();
                    fragmentTransaction.add(R.id.fragment,fragment3);
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
}