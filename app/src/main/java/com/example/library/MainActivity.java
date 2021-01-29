package com.example.library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.library.fragment.BookCityFragment;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_guide);//activity的布局

    /*启用父fragment*/
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.father_fg_container);//获取容器资源

        //具体书本页面暂时不考虑用fragment，因为不考虑左右滑动。只用activity。那么现在仅一个activity托管fg
        if (fragment == null){
            fragment = new BookCityFragment();
            fm.beginTransaction().
                    add(R.id.father_fg_container,fragment)
                    .commit();
        }

    }

}
