package com.example.library.fragment.sonfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.example.library.R;
import com.example.library.fragment.BookCityFragment;

public class RecommendFragment extends BookCityFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.son_fg_recommand,null);
        return view;
    }

    public void onPause(){
        super.onPause();
    }
}
