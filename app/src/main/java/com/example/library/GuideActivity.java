package com.example.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    /*底部导航栏的文字部分*/
    private TextView txt_book_city;//书城的文字
    private TextView txt_digest;//书摘的文字
    private TextView txt_mine;//我的的文字
    private FrameLayout fg_content;//中间的fragment部分的视图

    /*底部导航栏对应的Fragment*/
    private TextFG fragment1, fragment2, fragment3;//后续分别对应 书城，书摘，我的
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();//这一步有问题！！！！！！！！！！！！！！！！！！！
        txt_book_city.performClick();//模拟点击状态
        bindView();
    }


/*将实例事件与ui视图绑定*/
    private void bindView() {
        txt_book_city = (TextView) findViewById(R.id.txt_book_city);
        txt_digest = (TextView) findViewById(R.id.txt_digest);
        txt_mine = (TextView) findViewById(R.id.txt_mine);
        fg_content = (FrameLayout) findViewById(R.id.fg_content);

        /*设置监听器,使其变为可点击事件*/
        txt_book_city.setOnClickListener(this);
        txt_digest.setOnClickListener(this);
        txt_mine.setOnClickListener(this);

    }

    /*重置文本点击状态*/
    private void setSelect(){
        txt_book_city.setSelected(false);
        txt_digest.setSelected(false);
        txt_mine.setSelected(false);
    }




    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_book_city:setSelect();
            txt_book_city.setSelected(true);
            if(fragment1 == null){
                fragment1 = new TextFG("书城");
            }
            else
            {
                fTransaction.show(fragment1);
            }
                break;
        }

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